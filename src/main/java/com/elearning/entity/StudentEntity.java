package com.elearning.entity;

import com.elearning.entity.login.UserEntity;
import com.elearning.entity.sub.EnrollEntity;
import com.elearning.entity.sub.ResultEntity;
import com.elearning.helper.Gender;
import com.elearning.helper.StudentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"results", "enrolls", "account"})
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", unique = true)
    private String code;
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
    @Column(name = "address")
    private String address;
    @Column(name = "gpa")
    private double GPA;
    @Column(name = "total_credit")
    private int totalCredit;
    @Column(name = "status")
    private StudentStatus status;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentEntity department;
    @OneToMany(mappedBy = "student")
    private Set<ResultEntity> results;
    @OneToMany(mappedBy = "student")
    private Set<EnrollEntity> enrolls;
    @OneToOne(mappedBy = "student")
    private UserEntity account;

    public StudentEntity(String code, String firstName, String lastName,
                         Date dateOfBirth, Gender gender, String email, String phoneNumber,
                         String address, DepartmentEntity department) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
    }

    public StudentEntity(Long id, String code, String firstName, String lastName,
                         Date dateOfBirth, Gender gender, String email, String phoneNumber,
                         String address, DepartmentEntity department) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
    }

    public StudentEntity(String code, String firstName, String lastName) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
