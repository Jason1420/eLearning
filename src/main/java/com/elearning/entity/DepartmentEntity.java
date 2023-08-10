package com.elearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<TeacherEntity> teachers;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<StudentEntity> students;

    public DepartmentEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
