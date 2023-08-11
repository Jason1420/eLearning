package com.elearning.entity;

import com.elearning.entity.sub.ClassEntity;
import com.elearning.entity.sub.ResultEntity;
import com.elearning.helper.ExamType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "exam")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private ExamType type;
    @OneToMany(mappedBy = "exam")
    @JsonIgnore
    private Set<ResultEntity> results;
    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private ClassEntity clas;
    private Integer coefficient;

    public ExamEntity(String name, ExamType type, ClassEntity clas) {
        this.name = name;
        this.type = type;
        this.clas = clas;
    }

    public ExamEntity(String name, ExamType type) {
        this.name = name;
        this.type = type;
    }

    public ExamEntity(String name, ExamType type, ClassEntity clas, Integer coefficient) {
        this.name = name;
        this.type = type;
        this.clas = clas;
        this.coefficient = coefficient;
    }
}
