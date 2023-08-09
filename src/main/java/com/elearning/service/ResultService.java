package com.elearning.service;

import com.elearning.dto.helper.ResultExamDTO;
import com.elearning.dto.sub.ResultDTO;

import java.util.List;

public interface ResultService {
    ResultDTO markExam(ResultExamDTO dto);

    ResultDTO updateScoreExam(Long id, ResultExamDTO dto);

    void deleteResult(Long id);

    List<ResultDTO> viewResultOfExam(Long examId);

    List<ResultDTO> viewResultOfStudent(Long studentId);

    List<ResultDTO> findAllResult();
}
