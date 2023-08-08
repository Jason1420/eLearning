package com.elearning.service.impl;

import com.elearning.converter.TeacherConverter;
import com.elearning.dto.TeacherDTO;
import com.elearning.entity.TeacherEntity;
import com.elearning.repository.TeacherRepository;
import com.elearning.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherConverter teacherConverter;

    @Override
    public String updateTeacher(Long id, TeacherDTO dto) {
        TeacherEntity savedEntity = teacherRepository.save(teacherConverter.toEntity(dto,
                teacherRepository.findOneById(id)));
        return "Teacher id = " + savedEntity.getId() + " was updated!";
    }

    @Override
    public String deleteTeacher(Long id) {
        teacherRepository.delete(teacherRepository.findOneById(id));
        return "Teacher id = " + id + " was deleted!";
    }

    @Override
    public TeacherEntity showTeacher(Long id) {
        return teacherRepository.findOneById(id);
    }

    @Override
    public List<TeacherEntity> showAllTeacher() {
        return teacherRepository.findAll();
    }
}
