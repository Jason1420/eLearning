package com.elearning.entity.embeddable;

import com.elearning.entity.SubjectEntity;
import com.elearning.entity.TeacherEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
public class DeliverID implements Serializable {
    @Column(name ="created_date")
    private Date created_date;
    @Column(name ="class_name")
    private String name;
    @ManyToOne
    private TeacherEntity teacher;
    @ManyToOne
    private SubjectEntity subject;
}
