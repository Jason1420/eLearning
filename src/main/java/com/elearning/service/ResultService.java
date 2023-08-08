package com.elearning.service;

import com.elearning.dto.helper.ResultExamDTO;
import com.elearning.entity.sub.ResultEntity;

import java.util.List;

public interface ResultService {
    String markExam(ResultExamDTO dto);

    String updateScoreExam(Long id, ResultExamDTO dto);

    String deleteResult(Long id);

    List<ResultEntity> viewResultOfExam(Long examId);

    List<ResultEntity> viewResultOfStudent(Long studentId);
}
