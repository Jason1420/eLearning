package com.elearning.service.impl;

import com.elearning.converter.ExamConverter;
import com.elearning.dto.ExamDTO;
import com.elearning.dto.helper.CreateExamDTO;
import com.elearning.entity.ExamEntity;
import com.elearning.entity.sub.ClassEntity;
import com.elearning.exception.Exception404;
import com.elearning.repository.ExamRepository;
import com.elearning.repository.sub.ClassRepository;
import com.elearning.service.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final ExamConverter examConverter;
    private final ClassRepository classRepository;

    @Override
    public ExamDTO createExam(CreateExamDTO dto) {
        ClassEntity classEntity = classRepository.findOneById(dto.getClassID());
        if (classEntity == null) {
            throw new Exception404("This class is not found!");
        }
        return examConverter.toDTO(examRepository.save(
                new ExamEntity(dto.getName(), dto.getType(), classEntity)));
    }

    @Override
    public ExamDTO updateExam(Long id, ExamDTO dto) {
        checkExists(id);
        if (examRepository.findOneById(id) == null) {
            throw new Exception404("This Exam is not found");
        }
        return examConverter.toDTO(examRepository.save(
                examConverter.toEntity(dto, examRepository.findOneById(id))));

    }

    @Override
    public void deleteExam(Long id) {
        checkExists(id);
        examRepository.delete(examRepository.findOneById(id));
    }

    @Override
    public ExamDTO findOneExam(Long id) {
        checkExists(id);
        return examConverter.toDTO(examRepository.findOneById(id));
    }

    @Override
    public List<ExamDTO> findAllExam() {
        return examRepository.findAll().stream()
                .map(examConverter::toDTO)
                .collect(Collectors.toList());
    }

    public void checkExists(Long id) {
        if (examRepository.findOneById(id) == null) {
            throw new Exception404("Exam not found with this id");
        }
    }
}
