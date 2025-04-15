package jpa.projectuseentitymanager.entity;

import jpa.projectuseentitymanager.typeVariable.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Student")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "studentName")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Course> course;

    public void addCourse(Course courseAdd) {
        if(course == null){
            course = new ArrayList<>();
        }
        this.course.add(courseAdd);
    }

    public void removeCourse(Course courseRemove) {
        if(course != null){
            course.remove(courseRemove);
        }
    }
}
