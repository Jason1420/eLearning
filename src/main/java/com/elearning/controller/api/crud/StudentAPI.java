package com.elearning.controller.api.crud;

import com.elearning.dto.StudentDTO;
import com.elearning.entity.login.UserEntity;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.repository.security.UserRepository;
import com.elearning.service.StudentService;
import com.elearning.service.security.AccountService;
import com.elearning.service.security.CustomUserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentAPI {
    private final StudentService studentService;
    private final AccountService accountService;
    private final CustomUserDetailServiceImpl customUserDetailService;
    @PutMapping("/{id}")
    public Result updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto) {
        if(customUserDetailService.checkUserId(id)){
        StudentDTO savedDTO = studentService.updateStudent(id, dto);
        return new Result(true, StatusCode.SUCCESS, "Update success", savedDTO);
        }
        return new Result(false, StatusCode.FORBIDDEN, "FORBIDDEN");
    }

    @DeleteMapping("/{id}")
    public Result deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new Result(true, StatusCode.SUCCESS, "Delete success");
    }

    @GetMapping("/{id}")
    public Result findOneStudent(@PathVariable Long id) {
        if(customUserDetailService.checkUserId(id)){
            StudentDTO studentDTO = studentService.findOneStudent(id);
            return new Result(true, StatusCode.SUCCESS, "Find one success", studentDTO);
        }
        return new Result(false, StatusCode.FORBIDDEN, "FORBIDDEN");
    }

    @GetMapping
    public Result findAllStudent() {
        List<StudentDTO> listDTO = studentService.findAllStudent();
        return new Result(true, StatusCode.SUCCESS, "Find all success", listDTO);
    }
}
