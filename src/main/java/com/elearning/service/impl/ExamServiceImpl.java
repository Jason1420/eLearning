package com.elearning.service.impl;

import com.elearning.converter.ExamConverter;
import com.elearning.dto.ExamDTO;
import com.elearning.entity.ExamEntity;
import com.elearning.repository.ExamRepository;
import com.elearning.service.ExamService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final ExamConverter examConverter;

    @Override
    public String createExam(ExamDTO dto) {
        ExamEntity savedEntity = examRepository.save(examConverter.toEntity(dto));
        return savedEntity.getName() + " was created!";
    }

    @Override
    public String updateExam(Long id, ExamDTO dto) {
        ExamEntity oldEntity = examRepository.findOneById(id);
        if(oldEntity == null){
            throw new EntityNotFoundException("This Exam is not found");
        }
        ExamEntity savedEntity = examRepository.save(examConverter.toEntity(dto,oldEntity));
        return "Exam id = " + savedEntity.getId() +" was updated!";
    }

    @Override
    public String deleteExam(Long id) {
        examRepository.delete(examRepository.findOneById(id));
        return "Exam id = " + id +" was deleted!";
    }

    @Override
    public ExamEntity showExam(Long id) {
        return examRepository.findOneById(id);
    }

    @Override
    public List<ExamEntity> showAllExam() {
        return examRepository.findAll();
    }
}
