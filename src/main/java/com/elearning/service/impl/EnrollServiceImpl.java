package com.elearning.service.impl;

import com.elearning.dto.helper.EnrollClassDTO;
import com.elearning.entity.StudentEntity;
import com.elearning.entity.sub.ClassEntity;
import com.elearning.entity.sub.EnrollEntity;
import com.elearning.repository.StudentRepository;
import com.elearning.repository.sub.ClassRepository;
import com.elearning.repository.sub.EnrollRepository;
import com.elearning.service.EnrollService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EnrollServiceImpl implements EnrollService {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final EnrollRepository enrollRepository;

    @Override
    public String enrollClass(EnrollClassDTO dto) {
        StudentEntity studentEntity = studentRepository.findOneById(dto.getStudentId());
        if (studentEntity == null) {
            throw new EntityNotFoundException("Student is not found!");
        }
        ClassEntity classEntity = classRepository.findOneById(dto.getClassId());
        if (classEntity == null) {
            throw new EntityNotFoundException("Class is not found!");
        }
        EnrollEntity entity = new EnrollEntity(studentEntity, classEntity);
        enrollRepository.save(entity);
        return "Successfully enroll class id = " + classEntity.getId();
    }

    @Override
    public String deleteEnrolledClass(Long id) {
        enrollRepository.delete(enrollRepository.findOneById(id));
        return "Student was canceled enrolled class!";
    }

    @Override
    public List<EnrollEntity> showAllEnrolledClass() {
        return enrollRepository.findAll();
    }
}
