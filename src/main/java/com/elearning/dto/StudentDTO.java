package com.elearning.dto;

import com.elearning.dto.login.UserDTO;
import com.elearning.dto.sub.EnrollDTO;
import com.elearning.dto.sub.ResultDTO;
import com.elearning.helper.Gender;
import com.elearning.helper.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

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
    private Set<ResultDTO> results;
    private Set<EnrollDTO> enrolls;
    private UserDTO account;
}
