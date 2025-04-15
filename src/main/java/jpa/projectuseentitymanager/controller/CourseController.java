package jpa.projectuseentitymanager.controller;

import jpa.projectuseentitymanager.entity.Course;
import jpa.projectuseentitymanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping()
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(int id) {
        Course course = courseService.getCourse(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }

    @PostMapping()
    public ResponseEntity<String> createCourse(@RequestBody List<Course> course) {
        for (Course courseSave : course) {
            courseService.saveCourse(courseSave);
        }
        return ResponseEntity.ok("Add course successfully!!!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable int id) {
        Course courseUpdate = courseService.updateCourse(id,course);
        return ResponseEntity.ok(courseUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Delete successful!!!");
    }
}
