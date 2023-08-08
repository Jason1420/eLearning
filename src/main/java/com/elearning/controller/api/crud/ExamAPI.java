package com.elearning.controller.api.crud;

import com.elearning.dto.ExamDTO;
import com.elearning.dto.helper.CreateExamDTO;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
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
    public Result createExam(@RequestBody CreateExamDTO dto) {
        ExamDTO savedDTO = examService.createExam(dto);
        return new Result(true, StatusCode.SUCCESS, "Add success", savedDTO);
    }

    @PutMapping("/{id}")
    public Result updateExam(@PathVariable("id") Long id, @RequestBody ExamDTO dto) {
        ExamDTO savedDTO = examService.updateExam(id, dto);
        return new Result(true, StatusCode.SUCCESS, "Update success", savedDTO);
    }

    @DeleteMapping("/{id}")
    public Result deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return new Result(true, StatusCode.SUCCESS, "Delete success");
    }

    @GetMapping("/{id}")
    public Result findOneExam(@PathVariable Long id) {
        ExamDTO dto = examService.findOneExam(id);
        return new Result(true, StatusCode.SUCCESS, "Find one success", dto);
    }

    @GetMapping
    public Result findAllExam() {
        List<ExamDTO> listDTO = examService.findAllExam();
        return new Result(true, StatusCode.SUCCESS, "Find all success", listDTO);
    }
}
