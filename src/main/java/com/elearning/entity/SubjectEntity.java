package com.elearning.entity;

import com.elearning.entity.sub.ClassEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "code", unique = true)
    private String code;
    @Column(name = "credit")
    private int credit;
    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private Set<ClassEntity> classes;

    public SubjectEntity(Long id, String name, String code, int credit) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.credit = credit;
    }

    public void setCredit(int credit) {
        if (credit > 12) {
            this.credit = 12;
        } else if (credit < 0) {
            this.credit = 0;
        } else {
            this.credit = credit;
        }
    }
}
