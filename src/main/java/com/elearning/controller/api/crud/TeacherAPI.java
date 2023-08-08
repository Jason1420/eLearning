package com.elearning.controller.api.crud;

import com.elearning.dto.TeacherDTO;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@AllArgsConstructor
public class TeacherAPI {
    private final TeacherService teacherService;

    @PutMapping("/{id}")
    public Result updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO dto) {
        TeacherDTO savedDTO = teacherService.updateTeacher(id, dto);
        return new Result(true, StatusCode.SUCCESS, "Update success", savedDTO);
    }

    @DeleteMapping("/{id}")
    public Result deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return new Result(true, StatusCode.SUCCESS, "Delete success");
    }

    @GetMapping("/{id}")
    public Result showTeacher(@PathVariable Long id) {
        TeacherDTO teacherDTO = teacherService.findOneTeacher(id);
        return new Result(true, StatusCode.SUCCESS, "Find one success", teacherDTO);
    }

    @GetMapping
    public Result showAllTeacher() {
        List<TeacherDTO> listDTO = teacherService.findAllTeacher();
        return new Result(true, StatusCode.SUCCESS, "Find all success", listDTO);
    }
}
