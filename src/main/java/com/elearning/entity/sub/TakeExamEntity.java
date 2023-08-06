package com.elearning.entity.sub;

import com.elearning.entity.embeddable.TakeExamID;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="student_take_exam")
@Data
public class TakeExamEntity {
    @EmbeddedId
    private TakeExamID id;
    private double score;

}
