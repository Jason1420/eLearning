package com.elearning.controller.login;

import com.elearning.dto.helper.ChangePasswordDTO;
import com.elearning.dto.login.UserDTO;
import com.elearning.entity.login.UserEntity;
import com.elearning.service.security.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserAPI {
    private final AccountService accountService;

    @PostMapping("/register/student")
    public String createStudentAccount(@RequestBody UserDTO dto) {
        return accountService.createStudentAccount(dto);
    }

    @PostMapping("/register/teacher")
    public String createTeacherAccount(@RequestBody UserDTO dto) {
        return accountService.createTeacherAccount(dto);
    }

    @PutMapping("/user/{id}")
    public String changPassword(@PathVariable("id") Long id, @RequestBody ChangePasswordDTO dto) {
        return accountService.changePassword(id, dto);
    }

    @GetMapping("/user/{id}")
    public UserEntity showUser(@PathVariable("id") Long id) {
        return accountService.showUser(id);
    }

    @GetMapping("/user")
    public List<UserEntity> showAllUser() {
        return accountService.showAllUser();
    }
}
