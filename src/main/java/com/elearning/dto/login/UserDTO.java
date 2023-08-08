package com.elearning.dto.login;

import com.elearning.dto.StudentDTO;
import com.elearning.dto.TeacherDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;

    private String password;
    private String email;
//    private List<RoleDTO> roles;
    private TeacherDTO teacher;
    private StudentDTO student;
}
