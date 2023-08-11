package com.elearning.dto.login;

import com.elearning.dto.StudentDTO;
import com.elearning.dto.TeacherDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank(message = "User name shouldn't be blank")
    @Pattern(regexp = "^[a-zA-z][^\\W_]{7,14}$",
            message = "username must be 8-15 characters and must start with a letter")
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "password minimum eight characters, at least one uppercase letter," +
                    " one lowercase letter, one number and one special character")
    private String password;
    @Email(message = "Invalid Email")
    private String email;
    private TeacherDTO teacher;
    private StudentDTO student;

    public UserDTO(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
