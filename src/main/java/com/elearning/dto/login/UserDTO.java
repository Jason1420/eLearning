package com.elearning.dto.login;

import com.elearning.dto.StudentDTO;
import com.elearning.dto.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;

    public UserDTO(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    private String password;
    private String email;
    //    private List<RoleDTO> roles;
    private TeacherDTO teacher;
    private StudentDTO student;
}
