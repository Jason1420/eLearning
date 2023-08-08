package com.elearning.service.impl;

import com.elearning.converter.ResultConverter;
import com.elearning.dto.helper.ResultExamDTO;
import com.elearning.dto.sub.ResultDTO;
import com.elearning.entity.ExamEntity;
import com.elearning.entity.StudentEntity;
import com.elearning.entity.sub.ResultEntity;
import com.elearning.exception.Exception404;
import com.elearning.repository.ExamRepository;
import com.elearning.repository.StudentRepository;
import com.elearning.repository.sub.ResultRepository;
import com.elearning.service.ResultService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final ResultConverter resultConverter;
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;

    @Override
    public ResultDTO markExam(ResultExamDTO dto) {
        StudentEntity studentEntity = studentRepository.findOneById(dto.getStudentId());
        if (studentEntity == null) {
            throw new Exception404("This student is not found!");
        }
        ExamEntity examEntity = examRepository.findOneById(dto.getExamId());
        if (examEntity == null) {
            throw new Exception404("This exam is not found!");
        }
        return resultConverter.toDTO(resultRepository.save(
                new ResultEntity(studentEntity, examEntity, dto.getScore())));
    }

    @Override
    public ResultDTO updateScoreExam(Long id, ResultExamDTO dto) {
        ResultEntity resultEntity = resultRepository.findOneById(id);
        checkExists(id);
        resultEntity.setScore(dto.getScore());
        return resultConverter.toDTO(resultRepository.save(resultEntity));
    }

    @Override
    public void deleteResult(Long id) {
        checkExists(id);
        resultRepository.delete(resultRepository.findOneById(id));
    }

    @Override
    public List<ResultDTO> viewResultOfExam(Long examId) {
        if(resultRepository.searchByExamId(examId).size() == 0){
            throw new Exception404("Result not found with this exam id");
        }
        return resultRepository.searchByExamId(examId).stream()
                .map(resultConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResultDTO> viewResultOfStudent(Long studentId) {
        if(resultRepository.searchByStudentId(studentId).size() == 0){
            throw new Exception404("Result not found with this student id");
        }
        return resultRepository.searchByStudentId(studentId).stream()
                .map(resultConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResultDTO> findAllResult() {
        return resultRepository.findAll().stream()
                .map(resultConverter::toDTO)
                .collect(Collectors.toList());
    }

    public void checkExists(Long id) {
        if (resultRepository.findOneById(id) == null) {
            throw new Exception404("Result not found with this id");
        }
    }
}
