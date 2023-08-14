package com.elearning.dto;

import com.elearning.dto.login.UserDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private Long id;
    @NotBlank(message = "First name should not be blank")
    @Pattern(regexp = "^[a-zA-Z ]{1,25}$", message = "re-enter first name consisting of 1 to 25 alphanumeric characters")
    private String firstName;
    @NotBlank(message = "Last name should not be blank")
    @Pattern(regexp = "^[a-zA-Z ]{1,25}$", message = "re-enter last name consisting of 1 to 25 alphanumeric characters")
    private String lastName;
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-\\d{2}$", message = "re-enter date of birth, format: yyyy-MM-dd")
    private String dateOfBirth;
    @Pattern(regexp = "^(OTHER|FEMALE|MALE)$", message = "Gender format: MALE, FEMALE, OTHER")
    private String gender;
    @NotBlank(message = "email should not be blank")
    @Email(message = "Invalid email")
    private String email;
    @Pattern(regexp = "^0[0-9]{9}$", message = "Invalid phone number")
    private String phoneNumber;
    private DepartmentDTO department;
    private UserDTO account;

    public TeacherDTO(Long id, String firstName, String lastName, String dateOfBirth,
                      String gender, String email, String phoneNumber,
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

}
