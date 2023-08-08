package com.elearning.entity;

import com.elearning.entity.login.UserEntity;
import com.elearning.entity.sub.ClassEntity;
import com.elearning.helper.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public TeacherEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public TeacherEntity(String firstName, String lastName,
                         Date dateOfBirth, Gender gender, String email,
                         String phoneNumber, DepartmentEntity department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
    }

    public TeacherEntity(Long id, String firstName, String lastName,
                         Date dateOfBirth, Gender gender, String email,
                         String phoneNumber, DepartmentEntity department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentEntity department;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private Set<ClassEntity> classes;
    @OneToOne(mappedBy = "teacher")
    @JsonIgnore
    private UserEntity account;
}
