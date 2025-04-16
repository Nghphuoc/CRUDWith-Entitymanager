package jpa.projectuseentitymanager.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jpa.projectuseentitymanager.dao.StudentDao;
import jpa.projectuseentitymanager.entity.Course;
import jpa.projectuseentitymanager.entity.Student;
import jpa.projectuseentitymanager.exceptionHandle.ExceptionHandleFindCourse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentService implements StudentDao {

    @PersistenceContext
    // tiêm phụ thuộc
    private EntityManager entityManager;

    @Override
    public Student findStudent(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student findUserByName(String username) {
        // find user with name
        return (Student) entityManager.createQuery("select n from Student n where n.name=:username")
                .setParameter("username", username);
    }

    @Override
    public List<Student> findAllStudents() {
        return entityManager.createQuery("from Student", Student.class).getResultList();
    }

    @Override
    // yêu cầu sử dụng lại nếu có, sẽ tạo mới nếu không có
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveStudent(Student student) {
        List<Course> realCourses = new ArrayList<>();
        // duyệt qua danh sách course có trong khi add student
        if (student.getCourse() != null) {
            for (Course c : student.getCourse()) {
                // find course by id
                Course course = entityManager.find(Course.class, c.getId());
                if (course == null) {
                    // if not found
                    throw new ExceptionHandleFindCourse(c.getId());
                }
                // add to list
                realCourses.add(course);
            }
        }
        // set các course đã tìm thấy cho student
        student.setCourse(realCourses);
        entityManager.persist(student);
        return "Create student successfully";
    }

    @Override
    @Transactional
    public void updateStudent(int id, Student student) {
        // tìm lại student cũ
        Student studentOld = entityManager.find(Student.class, id);
        if (studentOld == null) {
            throw new EntityNotFoundException("Student not found with id: " + id);
        }
        // Lấy danh sách Course thật từ DB
        List<Course> realCourses = new ArrayList<>();
        if (student.getCourse() != null) {
            for (Course c : student.getCourse()) {
                Course course = entityManager.find(Course.class, c.getId());
                if (course == null) {
                    // if not found
                    throw new ExceptionHandleFindCourse(c.getId());
                }
                realCourses.add(course);

            }
        }
        // Cập nhật thông tin
        studentOld.setName(student.getName());
        studentOld.setEmail(student.getEmail());
        studentOld.setAge(student.getAge());
        studentOld.setGender(student.getGender());
        studentOld.setPhone(student.getPhone());
        // gán danh sách course đã kiểm tra
        studentOld.setCourse(realCourses);
        entityManager.merge(studentOld);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
        //entityManager.clear();
    }
}
