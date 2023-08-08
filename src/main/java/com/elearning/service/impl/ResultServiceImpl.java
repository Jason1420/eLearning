package com.elearning.service.impl;

import com.elearning.dto.helper.ResultExamDTO;
import com.elearning.entity.ExamEntity;
import com.elearning.entity.StudentEntity;
import com.elearning.entity.sub.ResultEntity;
import com.elearning.repository.ExamRepository;
import com.elearning.repository.StudentRepository;
import com.elearning.repository.sub.ResultRepository;
import com.elearning.service.ResultService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    @Override
    public String markExam(ResultExamDTO dto) {
        StudentEntity studentEntity = studentRepository.findOneById(dto.getStudentId());
        if (studentEntity == null) {
            throw new EntityNotFoundException("This student is not found!");
        }
        ExamEntity examEntity = examRepository.findOneById(dto.getExamId());
        if (examEntity == null) {
            throw new EntityNotFoundException("This exam is not found!");
        }
        ResultEntity resultEntity = new ResultEntity(studentEntity, examEntity, dto.getScore());
        resultRepository.save(resultEntity);
        return "Student id = " + dto.getStudentId() + " has score = " + dto.getScore() + " in exam id = " + dto.getExamId();
    }

    @Override
    public String updateScoreExam(Long id, ResultExamDTO dto) {
        ResultEntity resultEntity = resultRepository.findOneById(id);
        if (resultEntity == null) {
            throw new EntityNotFoundException("This result is not found!");
        }
        resultEntity.setScore(dto.getScore());
        resultRepository.save(resultEntity);
        return "Successfully updated result!";
    }

    @Override
    public String deleteResult(Long id) {
        resultRepository.delete(resultRepository.findOneById(id));
        return "Result id = " + id + " was deleted!";
    }

    @Override
    public List<ResultEntity> viewResultOfExam(Long examId) {
        return resultRepository.searchByExamId(examId);
    }

    @Override
    public List<ResultEntity> viewResultOfStudent(Long studentId) {
        return resultRepository.searchByExamId(studentId);
    }
}
