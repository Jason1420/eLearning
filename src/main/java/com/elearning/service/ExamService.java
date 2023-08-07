package com.elearning.service;

import com.elearning.dto.ExamDTO;
import com.elearning.entity.ExamEntity;

import java.util.List;

public interface ExamService {
    String createExam(ExamDTO dto);

    String updateExam(Long id, ExamDTO dto);

    String deleteExam(Long id);

    ExamEntity showExam(Long id);

    List<ExamEntity> showAllExam();
}
