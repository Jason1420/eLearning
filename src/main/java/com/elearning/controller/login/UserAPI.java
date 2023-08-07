package com.elearning.controller.login;

import com.elearning.dto.login.UserDTO;
import com.elearning.service.security.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserAPI {
    private final AccountService accountService;
    @PostMapping("/register/student")
    public String createStudentAccount(@RequestBody UserDTO dto){
        return accountService.createStudentAccount(dto);
    }
    @PostMapping("/register/teacher")
    public String createTeacherAccount(@RequestBody UserDTO dto){
        return accountService.createTeacherAccount(dto);
    }
}
