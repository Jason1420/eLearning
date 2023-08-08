package com.elearning.controller.api.crud;

import com.elearning.dto.helper.ResultExamDTO;
import com.elearning.entity.sub.ResultEntity;
import com.elearning.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ResultAPI {
    private ResultService resultService;

    @PostMapping("/teacher/result")
    public String markExam(@RequestBody ResultExamDTO dto) {
        return resultService.markExam(dto);
    }

    @PutMapping("/teacher/result/{id}")
    public String updateScore(@PathVariable("id") Long id, @RequestBody ResultExamDTO dto) {
        return resultService.updateScoreExam(id, dto);
    }

    @DeleteMapping("/teacher/result/{id}")
    public String deleteResult(@PathVariable("id") Long id) {
        return resultService.deleteResult(id);
    }

    @GetMapping("/result")
    public List<ResultEntity> viewResult(@RequestParam(value = "examId") Long examId,
                                         @RequestParam(value = "studentId") Long studentId) {
        if (examId != null) {
            return resultService.viewResultOfExam(examId);
            //not found
        }
        if (studentId != null) {
            return resultService.viewResultOfStudent(studentId);
            //not found
        }
        return null;
    }
}
