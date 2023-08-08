package com.elearning.service;

import com.elearning.dto.StudentDTO;
import com.elearning.entity.StudentEntity;

import java.util.List;

public interface StudentService {
    String updateStudent(Long id, StudentDTO dto);

    String deleteStudent(Long id);

    StudentEntity showStudent(Long id);

    List<StudentEntity> showAllStudent();
}
