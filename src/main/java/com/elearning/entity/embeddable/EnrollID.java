package com.elearning.entity.embeddable;

import com.elearning.entity.StudentEntity;
import com.elearning.entity.sub.DeliverEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class EnrollID implements Serializable {
    @ManyToOne
    private DeliverEntity deliver;
    @ManyToOne
    private StudentEntity student;
}
