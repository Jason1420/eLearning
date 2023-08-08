package com.elearning.controller.login;

import com.elearning.dto.helper.ChangePasswordDTO;
import com.elearning.dto.login.UserDTO;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.service.security.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserAPI {
    private final AccountService accountService;

    @PostMapping("/register/student")
    public Result createStudentAccount(@RequestBody UserDTO dto) {
        UserDTO savedDTO = accountService.createStudentAccount(dto);
        return new Result(true, StatusCode.SUCCESS, "Create student success!", savedDTO);
    }

    @PostMapping("/register/teacher")
    public Result createTeacherAccount(@RequestBody UserDTO dto) {
        UserDTO savedDTO = accountService.createTeacherAccount(dto);
        return new Result(true, StatusCode.SUCCESS, "Create teacher success!", savedDTO);
    }

    @PutMapping("/user/{id}")
    public Result changPassword(@PathVariable("id") Long id, @RequestBody ChangePasswordDTO dto) {
        accountService.changePassword(id, dto);
        return new Result(true, StatusCode.SUCCESS, "Change password success!");
    }

    @GetMapping("/user/{id}")
    public Result findOneUser(@PathVariable("id") Long id) {
        UserDTO dto = accountService.findOneUser(id);
        return new Result(true, StatusCode.SUCCESS, "Find one success!", dto);
    }

    @GetMapping("/user")
    public Result findAllUser() {
        List<UserDTO> listDTO = accountService.findAllUser();
        return new Result(true, StatusCode.SUCCESS, "Find one success!", listDTO);
    }
}
