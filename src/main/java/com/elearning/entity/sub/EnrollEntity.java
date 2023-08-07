package com.elearning.entity.sub;

import com.elearning.entity.StudentEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "enroll")
@Data
public class EnrollEntity {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student",referencedColumnName = "id")
    private StudentEntity student;
    @ManyToOne
    @JoinColumn(name = "class",referencedColumnName = "id")
    private ClassEntity clas;
}
