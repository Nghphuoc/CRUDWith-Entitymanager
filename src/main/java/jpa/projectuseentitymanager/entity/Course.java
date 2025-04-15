package jpa.projectuseentitymanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "course")
    private String nameCourse;
    @Column(name = "description")
    private String description;
    @Column(name = "location")
    private String location;
    @Column(name = "credits")
    private int credits;

    @ManyToMany(mappedBy = "course")
    @JsonBackReference
    private List<Student> student = new ArrayList<>();
}
