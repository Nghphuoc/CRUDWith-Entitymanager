package jpa.projectuseentitymanager.Dao;

import jpa.projectuseentitymanager.entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseDao {
    void saveCourse(Course course);
    void deleteCourse(int id);
    Course updateCourse(int id, Course course);
    List<Course> getAllCourses();
    Course getCourse(int id);
}
