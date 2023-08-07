package com.elearning.entity;

import com.elearning.entity.sub.ClassEntity;
import com.elearning.helper.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="teacher")
@Data
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "gender", length = 6)
    private Gender gender;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "department",referencedColumnName = "id")
    private DepartmentEntity department;
    @OneToMany(mappedBy = "teacher")
    private Set<ClassEntity> classes;

}
