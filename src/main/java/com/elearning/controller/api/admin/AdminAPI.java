package com.elearning.controller.api.admin;

import com.elearning.dto.login.RoleDTO;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.service.security.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminAPI {
    private final AccountService accountService;

    @PostMapping("/role")
    public Result addRole(@RequestBody String role) {
        RoleDTO roleDTO = accountService.addNewRole(role);
        return new Result(true, StatusCode.SUCCESS, "Add success!", roleDTO);
    }

    @DeleteMapping("/user/{id}")
    public Result deleteUser(@PathVariable Long id) {
        accountService.deleteUser(id);
        return new Result(true, StatusCode.SUCCESS, "Delete success!");
    }
}
