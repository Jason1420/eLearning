package com.elearning.dto;

import com.elearning.dto.login.UserDTO;
import com.elearning.dto.sub.ClassDTO;
import com.elearning.helper.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

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
    private Set<ClassDTO> classes;
    private UserDTO account;
}
