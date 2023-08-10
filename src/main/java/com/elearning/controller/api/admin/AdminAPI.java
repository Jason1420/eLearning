package com.elearning.controller.api.admin;

import com.elearning.dto.login.RoleDTO;
import com.elearning.dto.login.UserDTO;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.service.AdminService;
import com.elearning.service.security.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminAPI {
    private final AccountService accountService;
    private final AdminService adminService;

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

    @PostMapping("/result/student/{id}")
    public Result updateFinalResult(@PathVariable Long id) {
        List<List<Object>> updatedDTO = adminService.updateFinalResult(id);
        return new Result(true, StatusCode.SUCCESS, "Update final result success!", updatedDTO);
    }

    @PostMapping("/result/student")
    public Result updateResultToStudent() {
        adminService.updateResultToStudent();
        return new Result(true, StatusCode.SUCCESS, "Update result to student success!");
    }

    @PutMapping("/user/{id}")
    public Result updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        UserDTO savedDTO = adminService.updateUser(id, userDTO);
        return new Result(true, StatusCode.SUCCESS, "Update user success!", savedDTO);
    }

    @GetMapping("/result")
    public Result findAllResult() {
        List<List<Object>> list = adminService.findAllResult();
        return new Result(true, StatusCode.SUCCESS, "Find all success, " +
                "[student_id, class_id, exam_id, exam_type, result_score, subject_credit]", list);
    }
}
