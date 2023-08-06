package com.elearning.entity.sub;

import com.elearning.entity.embeddable.DeliverID;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "teacher_deliver_subject")
@Data
public class DeliverEntity {
    @EmbeddedId
    private DeliverID id;
}
