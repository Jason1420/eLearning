package com.elearning.entity.sub;

import com.elearning.entity.StudentEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "final_result")
@Data
@NoArgsConstructor
public class FinalResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private ClassEntity clas;
    private int credit;
    private double score;

    public FinalResultEntity(StudentEntity student, ClassEntity clas, int credit, double score) {
        this.student = student;
        this.clas = clas;
        this.credit = credit;
        if (score > 10.0) {
            this.score = 10.0;
        } else if (score < 0) {
            this.score = 0;
        } else {
            this.score = score;
        }
    }

    public FinalResultEntity(Long id, StudentEntity student, ClassEntity clas, int credit, double score) {
        this.id = id;
        this.student = student;
        this.clas = clas;
        this.credit = credit;
        if (score > 10.0) {
            this.score = 10.0;
        } else if (score < 0) {
            this.score = 0;
        } else {
            this.score = score;
        }
    }

}
