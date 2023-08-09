package com.elearning.entity.sub;

import com.elearning.entity.SubjectEntity;
import com.elearning.entity.TeacherEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "class")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private SubjectEntity subject;
    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private TeacherEntity teacher;
    @OneToMany(mappedBy = "clas")
    @JsonIgnore
    private Set<EnrollEntity> enrolls;

    public ClassEntity(String name, SubjectEntity subject, TeacherEntity teacher) {
        this.name = name;
        this.subject = subject;
        this.teacher = teacher;
    }

    public ClassEntity(Long id, String name, SubjectEntity subject, TeacherEntity teacher) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.teacher = teacher;
    }
}
