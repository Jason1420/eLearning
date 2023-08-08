package com.elearning.controller.api.crud;

import com.elearning.dto.helper.CreateClassDTO;
import com.elearning.entity.sub.ClassEntity;
import com.elearning.service.ClassService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/class")
@AllArgsConstructor
public class ClassAPI {
    private final ClassService classService;

    /* CRUD Class */
    @PostMapping
    public String addClass(@RequestBody CreateClassDTO dto) {
        return classService.addClass(dto);
    }

    @PutMapping("/{id}")
    public String updateClass(@PathVariable Long id, @RequestBody CreateClassDTO dto) {
        return classService.updateClass(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteClass(@PathVariable Long id) {
        return classService.deleteClass(id);
    }

    @GetMapping("/{id}")
    public ClassEntity showClass(@PathVariable Long id) {
        return classService.showClass(id);
    }

    @GetMapping
    public List<ClassEntity> showAllClass() {
        return classService.showAllClass();
    }
}
