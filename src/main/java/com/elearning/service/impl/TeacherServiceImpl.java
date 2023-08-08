package com.elearning.service.impl;

import com.elearning.converter.TeacherConverter;
import com.elearning.dto.TeacherDTO;
import com.elearning.exception.Exception404;
import com.elearning.repository.TeacherRepository;
import com.elearning.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherConverter teacherConverter;

    @Override
    public TeacherDTO updateTeacher(Long id, TeacherDTO dto) {
        checkExists(id);
        return teacherConverter.toDTO(teacherRepository.save(
                teacherConverter.toEntity(dto, teacherRepository.findOneById(id))));
    }

    @Override
    public void deleteTeacher(Long id) {
        checkExists(id);
        teacherRepository.delete(teacherRepository.findOneById(id));
    }

    @Override
    public TeacherDTO findOneTeacher(Long id) {
        checkExists(id);
        return teacherConverter.toDTO(teacherRepository.findOneById(id));
    }

    @Override
    public List<TeacherDTO> findAllTeacher() {
        return teacherRepository.findAll().stream()
                .map(teacherConverter::toDTO)
                .collect(Collectors.toList());
    }

    public void checkExists(Long id) {
        if (teacherRepository.findOneById(id) == null) {
            throw new Exception404("Teacher not found with this id");
        }
    }
}
