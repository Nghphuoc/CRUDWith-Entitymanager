package jpa.projectuseentitymanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

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

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "studentId")
    @JsonIgnore
    private Student student;  // khúc này e tính lộn rùi !!! @ManyToMany mới đúng
}
