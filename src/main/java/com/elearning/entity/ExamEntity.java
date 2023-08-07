package com.elearning.entity;

import com.elearning.entity.sub.ResultEntity;
import com.elearning.helper.ExamType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="exam")
@Data
public class ExamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="name")
    private String name;
    @Column(name ="type")
    private ExamType type;
    @OneToMany(mappedBy = "exam")
    private Set<ResultEntity> results;

}
