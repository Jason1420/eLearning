package com.elearning.entity.sub;

import com.elearning.entity.ExamEntity;
import com.elearning.entity.StudentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "result")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private ExamEntity exam;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity student;
    private double score;

    public ResultEntity(StudentEntity student, ExamEntity exam, double score) {
        this.student = student;
        this.exam = exam;
        this.score = score;
    }

    public void setScore(double score) {
        if (score > 10.0) {
            this.score = 10.0;
        } else if (score < 0) {
            this.score = 0;
        } else {
            this.score = score;
        }
    }
}
