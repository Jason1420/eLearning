package com.elearning.entity.sub;

import com.elearning.entity.SubjectEntity;
import com.elearning.entity.TeacherEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "class")
@Data
@NoArgsConstructor
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

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

    @ManyToOne
    @JoinColumn(name = "subject",referencedColumnName = "id")
    private SubjectEntity subject;
    @ManyToOne
    @JoinColumn(name = "teacher",referencedColumnName = "id")
    private TeacherEntity teacher;
    @OneToMany(mappedBy = "clas")
    private Set<EnrollEntity> enrolls;
}
