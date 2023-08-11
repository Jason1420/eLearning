package com.elearning.service;

import com.elearning.dto.StudentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface StudentService {
    StudentDTO updateStudent(Long id, StudentDTO dto);

    void deleteStudent(Long id);

    StudentDTO findOneStudent(Long id);

    List<StudentDTO> findAllStudent();

    ByteArrayInputStream getActualData();

    void importStudentFromExcelFile(MultipartFile file);
}
