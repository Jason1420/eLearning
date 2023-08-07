package com.elearning.entity.sub;

import com.elearning.entity.ExamEntity;
import com.elearning.entity.StudentEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="result")
@Data
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student",referencedColumnName = "id")
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name = "exam",referencedColumnName = "id")
    private ExamEntity exam;

    private double score;

}
