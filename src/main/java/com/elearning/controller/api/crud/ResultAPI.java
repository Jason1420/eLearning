package com.elearning.controller.api.crud;

import com.elearning.dto.helper.ResultExamDTO;
import com.elearning.dto.sub.ResultDTO;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
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
    public Result markExam(@RequestBody ResultExamDTO dto) {
        ResultDTO savedDTO = resultService.markExam(dto);
        return new Result(true, StatusCode.SUCCESS, "Mark success", savedDTO);
    }

    @PutMapping("/teacher/result/{id}")
    public Result updateScore(@PathVariable("id") Long id, @RequestBody ResultExamDTO dto) {
        ResultDTO savedDTO = resultService.updateScoreExam(id, dto);
        return new Result(true, StatusCode.SUCCESS, "Update success", savedDTO);
    }

    @DeleteMapping("/teacher/result/{id}")
    public Result deleteResult(@PathVariable("id") Long id) {
        resultService.deleteResult(id);
        return new Result(true, StatusCode.SUCCESS, "Delete success");
    }

    @GetMapping("/result")
    public Result viewResult(@RequestParam(value = "examId", required = false) Long examId,
                             @RequestParam(value = "studentId", required = false) Long studentId) {
        if (examId != null) {
            List<ResultDTO> listDTO = resultService.viewResultOfExam(examId);
            return new Result(true, StatusCode.SUCCESS, "Search success", listDTO);
            //not found
        }
        if (studentId != null) {
            List<ResultDTO> listDTO = resultService.viewResultOfStudent(studentId);
            return new Result(true, StatusCode.SUCCESS, "Search success", listDTO);
            //not found
        }
        List<ResultDTO> listDTO = resultService.findAllResult();
        return new Result(true, StatusCode.SUCCESS, "Search success", listDTO);
    }
}
