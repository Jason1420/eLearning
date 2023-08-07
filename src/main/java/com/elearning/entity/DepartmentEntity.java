package com.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name="department")
@Data
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "department")
    private Set<TeacherEntity> teachers;
    @OneToMany(mappedBy = "department")
    private Set<StudentEntity> students;
}
