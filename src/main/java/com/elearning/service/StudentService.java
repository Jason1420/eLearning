package com.elearning.service;

import com.elearning.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    StudentDTO updateStudent(Long id, StudentDTO dto);

    void deleteStudent(Long id);

    StudentDTO findOneStudent(Long id);

    List<StudentDTO> findAllStudent();
}
