package com.elearning.controller.api.admin;

import com.elearning.service.security.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminAPI {
    private final AccountService accountService;

    @PostMapping("/role")
    public String addRole(@RequestBody String role) {
        return accountService.addNewRole(role);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        return accountService.deleteUser(id);
    }
}
