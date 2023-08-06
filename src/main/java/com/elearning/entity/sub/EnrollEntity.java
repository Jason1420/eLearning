package com.elearning.entity.sub;

import com.elearning.entity.embeddable.EnrollID;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student_enroll_class")
@Data
public class EnrollEntity {
    @EmbeddedId
    private EnrollID id;
}
