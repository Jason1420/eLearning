package com.elearning.controller.api.crud;

import com.elearning.dto.helper.EnrollClassDTO;
import com.elearning.entity.sub.EnrollEntity;
import com.elearning.service.EnrollService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student/enroll")
@AllArgsConstructor
public class EnrollAPI {
    private final EnrollService enrollService;

    @PostMapping
    public String enrollClass(@RequestBody EnrollClassDTO dto) {
        return enrollService.enrollClass(dto);
    }

    @DeleteMapping("/{id}")
    public String deleteEnrolledClass(@PathVariable Long id) {
        return enrollService.deleteEnrolledClass(id);
    }

    @GetMapping
    public List<EnrollEntity> showAllEnrolledClass() {
        return enrollService.showAllEnrolledClass();
    }
}
