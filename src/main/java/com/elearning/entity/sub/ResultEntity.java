package com.elearning.entity.sub;

import com.elearning.entity.ExamEntity;
import com.elearning.entity.StudentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="result")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
