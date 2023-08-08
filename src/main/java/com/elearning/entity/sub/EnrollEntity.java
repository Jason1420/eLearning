package com.elearning.entity.sub;

import com.elearning.entity.StudentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enroll")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private ClassEntity clas;

    public EnrollEntity(StudentEntity student, ClassEntity clas) {
        this.student = student;
        this.clas = clas;
    }
}
