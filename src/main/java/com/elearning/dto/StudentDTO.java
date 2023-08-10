package com.elearning.dto;

import com.elearning.dto.login.UserDTO;
import com.elearning.helper.Gender;
import com.elearning.helper.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String code;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private String address;
    private double GPA;
    private int totalCredit;
    private StudentStatus status;
    private DepartmentDTO department;
    private UserDTO account;

    public StudentDTO(Long id, String code, String firstName, String lastName, Date dateOfBirth, Gender gender,
                      String email, String phoneNumber, String address, DepartmentDTO department) {
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

}
