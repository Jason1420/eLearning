package com.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="subject")
@Data
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="name")
    private String name;
    @Column(name ="code")
    private String code;
    @Column(name ="credit")
    private int credit;


}
