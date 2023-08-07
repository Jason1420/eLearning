package com.elearning.entity.sub;

import com.elearning.entity.SubjectEntity;
import com.elearning.entity.TeacherEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "class")
@Data
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "subject",referencedColumnName = "id")
    private SubjectEntity subject;
    @ManyToOne
    @JoinColumn(name = "teacher",referencedColumnName = "id")
    private TeacherEntity teacher;
    @OneToMany(mappedBy = "clas ")
    private Set<EnrollEntity> enrolls;
}
