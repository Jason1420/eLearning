package com.elearning.controller.api.crud;

import com.elearning.dto.TeacherDTO;
import com.elearning.entity.TeacherEntity;
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
    public String updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO dto) {
        return teacherService.updateTeacher(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        return teacherService.deleteTeacher(id);
    }

    @GetMapping("/{id}")
    public TeacherEntity showTeacher(@PathVariable Long id) {
        return teacherService.showTeacher(id);
    }

    @GetMapping
    public List<TeacherEntity> showAllTeacher() {
        return teacherService.showAllTeacher();
    }
}
