package com.elearning.entity;

import com.elearning.entity.sub.ClassEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "code", unique = true)
    private String code;
    @Column(name = "credit")
    private int credit;

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private Set<ClassEntity> classes;

}
