package com.elearning.service;

import com.elearning.dto.ExamDTO;
import com.elearning.dto.helper.CreateExamDTO;

import java.util.List;

public interface ExamService {
    ExamDTO createExam(CreateExamDTO dto);

    ExamDTO updateExam(Long id, ExamDTO dto);

    void deleteExam(Long id);

    ExamDTO findOneExam(Long id);

    List<ExamDTO> findAllExam();
}
