package jpa.projectuseentitymanager.dao;

import jpa.projectuseentitymanager.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface StudentDao {
    Student findStudent(int id);
    Student findUserByName(String username);
    List<Student> findAllStudents();
    String saveStudent(Student student);
    void updateStudent(int id, Student student);
    void deleteStudent(int id);
}
