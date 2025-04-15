package jpa.projectuseentitymanager.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpa.projectuseentitymanager.Dao.CourseDao;
import jpa.projectuseentitymanager.entity.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CourseService implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    @Transactional
    public void deleteCourse(int id) {
        entityManager.remove(entityManager.find(Course.class, id));
    }

    @Override
    @Transactional
    public Course updateCourse(int id, Course course) {
        Course courseOld = entityManager.find(Course.class, id);
        courseOld.setNameCourse(course.getNameCourse());
        courseOld.setDescription(course.getDescription());
        courseOld.setCredits(course.getCredits());
        courseOld.setLocation(course.getLocation());
        return entityManager.merge(courseOld);
    }

    @Override
    public List<Course> getAllCourses() {
        return entityManager
                .createQuery(" select c from Course c", Course.class)
                .getResultList();
    }

    @Override
    public Course getCourse(int id) {
        return entityManager.find(Course.class, id);
    }
}
