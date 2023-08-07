package com.elearning.service.impl;

import com.elearning.converter.StudentConverter;
import com.elearning.dto.StudentDTO;
import com.elearning.entity.DepartmentEntity;
import com.elearning.entity.StudentEntity;
import com.elearning.repository.StudentRepository;
import com.elearning.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;
    @Override
    public String updateStudent(Long id, StudentDTO dto) {
        StudentEntity savedEntity = studentRepository.save(studentConverter.toEntity(dto,
                studentRepository.findOneById(id)));
        return "Student id = " + savedEntity.getId() + " was updated!";
    }

    @Override
    public String deleteStudent(Long id) {
        studentRepository.delete(studentRepository.findOneById(id));
        return "Student id = " + id + " was deleted!";
    }

    @Override
    public StudentEntity showStudent(Long id) {
        return studentRepository.findOneById(id);
    }

    @Override
    public List<StudentEntity> showAllStudent() {
        return studentRepository.findAll();
    }
}
