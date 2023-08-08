package com.elearning.controller.api.crud;

import com.elearning.dto.SubjectDTO;
import com.elearning.entity.SubjectEntity;
import com.elearning.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
@AllArgsConstructor
public class SubjectAPI {
    private final SubjectService subjectService;

    /* CRUD Subject */
    @PostMapping
    public String addSubject(@RequestBody SubjectDTO dto) {
        return subjectService.addSubject(dto);
    }

    @PutMapping("/{id}")
    public String updateSubject(@PathVariable Long id, @RequestBody SubjectDTO dto) {
        return subjectService.updateSubject(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Long id) {
        return subjectService.deleteSubject(id);
    }

    @GetMapping("/{id}")
    public SubjectEntity showSubject(@PathVariable Long id) {
        return subjectService.showSubject(id);
    }

    @GetMapping
    public List<SubjectEntity> showAllSubject() {
        return subjectService.showAllSubject();
    }
}
