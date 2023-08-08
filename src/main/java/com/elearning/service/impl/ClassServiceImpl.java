package com.elearning.service.impl;

import com.elearning.dto.helper.CreateClassDTO;
import com.elearning.entity.SubjectEntity;
import com.elearning.entity.TeacherEntity;
import com.elearning.entity.sub.ClassEntity;
import com.elearning.repository.SubjectRepository;
import com.elearning.repository.TeacherRepository;
import com.elearning.repository.sub.ClassRepository;
import com.elearning.service.ClassService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;


    @Override
    public String addClass(CreateClassDTO dto) {
        if (classRepository.findByName(dto.getClassName()) != null) {
            throw new EntityExistsException("This class had existed");
        }
        SubjectEntity subjectEntity = subjectRepository.findOneById(dto.getSubjectId());
        if (subjectEntity == null) {
            throw new EntityNotFoundException("Subject is not found!");
        }
        TeacherEntity teacherEntity = teacherRepository.findOneById(dto.getTeacherId());
        if (teacherEntity == null) {
            throw new EntityNotFoundException("Teacher is not found!");
        }
        ClassEntity entity = new ClassEntity(dto.getClassName(), subjectEntity, teacherEntity);
        ClassEntity savedEntity = classRepository.save(entity);
        return dto.getClassName() + " was created with class id = " + savedEntity.getId();
    }

    @Override
    public String updateClass(Long id, CreateClassDTO dto) {
        if (classRepository.findById(id) == null) {
            throw new EntityNotFoundException("This class is not found!");
        }
        SubjectEntity subjectEntity = subjectRepository.findOneById(dto.getSubjectId());
        if (subjectEntity == null) {
            throw new EntityNotFoundException("Subject is not found!");
        }
        TeacherEntity teacherEntity = teacherRepository.findOneById(dto.getTeacherId());
        if (teacherEntity == null) {
            throw new EntityNotFoundException("Teacher is not found!");
        }
        ClassEntity entity = new ClassEntity(id, dto.getClassName(), subjectEntity, teacherEntity);
        ClassEntity savedEntity = classRepository.save(entity);
        return dto.getClassName() + " was updated with class id = " + savedEntity.getId();
    }

    @Override
    public String deleteClass(Long id) {
        classRepository.delete(classRepository.findOneById(id));
        return "class id = " + id + " was deleted";
    }

    @Override
    public ClassEntity showClass(Long id) {
        return classRepository.findOneById(id);
    }

    @Override
    public List<ClassEntity> showAllClass() {
        return classRepository.findAll();
    }
}
