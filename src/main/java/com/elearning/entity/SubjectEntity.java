package com.elearning.entity;

import com.elearning.entity.sub.ClassEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name="subject")
@Data
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="name",unique = true)
    private String name;
    @Column(name ="code",unique = true)
    private String code;
    @Column(name ="credit")
    private int credit;

    @OneToMany(mappedBy = "subject")
    private Set<ClassEntity> classes;

}
