package com.elearning.controller.api;

import com.elearning.dto.ExamDTO;
import com.elearning.entity.ExamEntity;
import com.elearning.entity.SubjectEntity;
import com.elearning.service.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exam")
@AllArgsConstructor
public class ExamAPI {
    private final ExamService examService;
    @PostMapping
    public String createExam(@RequestBody ExamDTO dto){
        return examService.createExam(dto);
    }
    @PutMapping("/{id}")
    public String updateExam(@PathVariable("id") Long id, @RequestBody ExamDTO dto){
        return examService.updateExam(id,dto);
    }
    @DeleteMapping("/{id}")
    public String deleteExam(@PathVariable Long id){
        return examService.deleteExam(id);
    }
    @GetMapping("/{id}")
    public ExamEntity showSubject(@PathVariable Long id){
        return examService.showExam(id);
    }
    @GetMapping()
    public List<ExamEntity> showAllSubject(){
        return examService.showAllExam();
    }
}
