package jpa.projectuseentitymanager.controller;

import jpa.projectuseentitymanager.entity.Student;
import jpa.projectuseentitymanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<Student>> addStudent() {
       List<Student> students = studentService.findAllStudents();
       if (students.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        studentService.findStudent(id);
        return new ResponseEntity<>(studentService.findStudent(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        studentService.updateStudent(id,student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addStudent(@RequestBody List<Student> student) {
        // có thể add nhiều or 1 student
        for(Student studentSave : student) {
            studentService.saveStudent(studentSave);
        }
        return new ResponseEntity<>("Save successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>(studentService.findStudent(id), HttpStatus.OK);
    }

    @GetMapping("/findByName")
    public ResponseEntity<Student> findByName(@RequestParam String name) {
        Student student = studentService.findUserByName(name);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
