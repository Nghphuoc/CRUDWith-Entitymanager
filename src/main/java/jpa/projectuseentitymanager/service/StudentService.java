package jpa.projectuseentitymanager.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jpa.projectuseentitymanager.Dao.StudentDao;
import jpa.projectuseentitymanager.entity.Course;
import jpa.projectuseentitymanager.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class StudentService implements StudentDao {

    @PersistenceContext // tiêm phụ thuộc
    private EntityManager entityManager;

    @Override
    public Student findStudent(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student findUserByName(String username) {
        return (Student) entityManager.createQuery("select n from Student n where n.name=:username").setParameter("username", username);
    }

    @Override
    public List<Student> findAllStudents() {
        return entityManager.createQuery("from Student", Student.class).getResultList();
    }

    @Override
    @Transactional
    public String saveStudent(Student student) {
        List<Course> realCourses = new ArrayList<>();
        // duyệt qua tất cả course trong student
        for (Course c : student.getCourse()) {
            Course course = entityManager.find(Course.class, c.getId());
            if (course != null) {
                course.setStudent(student);  // gán student cho course
                realCourses.add(course);
            }
        }

        student.setCourse(realCourses);
        entityManager.persist(student);
        return "Create student Successfully";
    }

    @Override
    @Transactional
    public void updateStudent(int id, Student student) {
        List<Course> realCourses = new ArrayList<>();
        Student studentOld = entityManager.find(Student.class, id);
        if (student.getCourse() != null) {
            for(Course courseSave : student.getCourse()) {
                courseSave.setStudent(studentOld);
                realCourses.add(courseSave);
            }
        }
        student.setCourse(realCourses);
        studentOld.setName(student.getName());
        studentOld.setEmail(student.getEmail());
        studentOld.setAge(student.getAge());
        studentOld.setGender(student.getGender());
        studentOld.setPhone(student.getPhone());
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
