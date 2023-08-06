package com.elearning.entity;

import com.elearning.helper.Gender;
import com.elearning.helper.StudentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="student")
@Data
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
//    @JoinColumn(name = "department",referencedColumnName = "id")
    private DepartmentEntity department;
}
