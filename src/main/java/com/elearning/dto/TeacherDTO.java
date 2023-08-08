package com.elearning.dto;

import com.elearning.dto.login.UserDTO;
import com.elearning.helper.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private DepartmentDTO department;
    private UserDTO account;

    public TeacherDTO(Long id, String firstName, String lastName, Date dateOfBirth,
                      Gender gender, String email, String phoneNumber,
                      DepartmentDTO department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
    }

    //    private Set<ClassDTO> classes;
}
