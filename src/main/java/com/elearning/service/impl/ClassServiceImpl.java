package com.elearning.service.impl;

import com.elearning.converter.ClassConverter;
import com.elearning.dto.helper.CreateClassDTO;
import com.elearning.dto.sub.ClassDTO;
import com.elearning.entity.SubjectEntity;
import com.elearning.entity.TeacherEntity;
import com.elearning.entity.sub.ClassEntity;
import com.elearning.exception.Exception404;
import com.elearning.exception.Exception409;
import com.elearning.repository.SubjectRepository;
import com.elearning.repository.TeacherRepository;
import com.elearning.repository.sub.ClassRepository;
import com.elearning.service.ClassService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final ClassConverter classConverter;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public ClassDTO addClass(CreateClassDTO dto) {
        if (classRepository.findByName(dto.getClassName()) != null) {
            throw new Exception409("Class name already exists");
        }
        SubjectEntity subjectEntity = subjectRepository.findOneById(dto.getSubjectId());
        if (subjectEntity == null) {
            throw new Exception404("Subject is not found!");
        }
        TeacherEntity teacherEntity = teacherRepository.findOneById(dto.getTeacherId());
        if (teacherEntity == null) {
            throw new Exception404("Teacher is not found!");
        }
        return classConverter.toDTO(classRepository.save(
                new ClassEntity(dto.getClassName(), subjectEntity, teacherEntity)));
    }

    @Override
    public ClassDTO updateClass(Long id, CreateClassDTO dto) {
        checkExists(id);
        if (!classRepository.findOneById(id).equals(classRepository.findOneByName(dto.getClassName()))) {
            if (classRepository.findOneByName(dto.getClassName()) != null) {
                throw new Exception409("Class name already exists");
            }
        }
        SubjectEntity subjectEntity = subjectRepository.findOneById(dto.getSubjectId());
            if (subjectEntity == null) {
                throw new Exception404("Subject is not found!");
            }
            TeacherEntity teacherEntity = teacherRepository.findOneById(dto.getTeacherId());
            if (teacherEntity == null) {
                throw new Exception404("Teacher is not found!");
        }
        return classConverter.toDTO(classRepository.save(
                new ClassEntity(id, dto.getClassName(), subjectEntity, teacherEntity)));
    }

    @Override
    public void deleteClass(Long id) {
        checkExists(id);
        classRepository.delete(classRepository.findOneById(id));
    }

    @Override
    public ClassDTO findOneClass(Long id) {
        checkExists(id);
        return classConverter.toDTO(classRepository.findOneById(id));
    }

    @Override
    public List<ClassDTO> findAllClass() {
        return classRepository.findAll().stream()
                .map(classConverter::toDTO)
                .collect(Collectors.toList());
    }

    public void checkExists(Long id) {
        if (classRepository.findOneById(id) == null) {
            throw new Exception404("Class not found with this id");
        }
    }
}
