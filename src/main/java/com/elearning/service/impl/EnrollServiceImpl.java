package com.elearning.service.impl;

import com.elearning.converter.EnrollConverter;
import com.elearning.dto.helper.EnrollClassDTO;
import com.elearning.dto.sub.EnrollDTO;
import com.elearning.entity.StudentEntity;
import com.elearning.entity.sub.ClassEntity;
import com.elearning.entity.sub.EnrollEntity;
import com.elearning.exception.Exception404;
import com.elearning.repository.StudentRepository;
import com.elearning.repository.sub.ClassRepository;
import com.elearning.repository.sub.EnrollRepository;
import com.elearning.service.EnrollService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EnrollServiceImpl implements EnrollService {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final EnrollRepository enrollRepository;
    private final EnrollConverter enrollConverter;

    @Override
    public EnrollDTO enrollClass(EnrollClassDTO dto) {
        StudentEntity studentEntity = studentRepository.findOneById(dto.getStudentId());
        if (studentEntity == null) {
            throw new Exception404("Student is not found!");
        }
        ClassEntity classEntity = classRepository.findOneById(dto.getClassId());
        if (classEntity == null) {
            throw new Exception404("Class is not found!");
        }
        return enrollConverter.toDTO(enrollRepository.save(new EnrollEntity(studentEntity, classEntity)));
    }

    @Override
    public void deleteEnrolledClass(Long id) {
        checkExists(id);
        enrollRepository.delete(enrollRepository.findOneById(id));
    }

    @Override
    public EnrollDTO findOneEnrolledClass(Long id) {
        checkExists(id);
        return enrollConverter.toDTO(enrollRepository.findOneById(id));
    }

    @Override
    public List<EnrollDTO> findAllEnrolledClass() {
        return enrollRepository.findAll().stream()
                .map(enrollConverter::toDTO)
                .collect(Collectors.toList());
    }

    public void checkExists(Long id) {
        if (enrollRepository.findOneById(id) == null) {
            throw new Exception404("EnrolledClass not found with this id");
        }
    }
}
