package com.elearning.entity.embeddable;

import com.elearning.entity.ExamEntity;
import com.elearning.entity.sub.EnrollEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class TakeExamID implements Serializable {
    //    @ManyToOne
//    private DeliverEntity deliver;
    @ManyToOne
    private ExamEntity exam;
    @ManyToOne
    private EnrollEntity enroll;
}
