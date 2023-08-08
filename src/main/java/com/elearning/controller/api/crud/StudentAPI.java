package com.elearning.controller.api.crud;

import com.elearning.dto.StudentDTO;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentAPI {
    private final StudentService studentService;

    @PutMapping("/{id}")
    public Result updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto) {
        StudentDTO savedDTO = studentService.updateStudent(id, dto);
        return new Result(true, StatusCode.SUCCESS, "Update success", savedDTO);
    }

    @DeleteMapping("/{id}")
    public Result deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new Result(true, StatusCode.SUCCESS, "Delete success");
    }

    @GetMapping("/{id}")
    public Result findOneStudent(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.findOneStudent(id);
        return new Result(true, StatusCode.SUCCESS, "Find one success", studentDTO);
    }

    @GetMapping
    public Result findAllStudent() {
        List<StudentDTO> listDTO = studentService.findAllStudent();
        return new Result(true, StatusCode.SUCCESS, "Find all success", listDTO);
    }
}
