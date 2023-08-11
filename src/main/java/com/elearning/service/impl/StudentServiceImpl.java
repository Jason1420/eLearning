package com.elearning.service.impl;

import com.elearning.converter.StudentConverter;
import com.elearning.dto.StudentDTO;
import com.elearning.entity.StudentEntity;
import com.elearning.exception.Exception404;
import com.elearning.exception.Exception409;
import com.elearning.filecsv.Helper;
import com.elearning.repository.StudentRepository;
import com.elearning.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;
    private final Helper helper;

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        checkExists(id);
        if (!studentRepository.findOneById(id).getCode().equals(dto.getCode())) {
            if (studentRepository.findOneByCode(dto.getCode()) != null) {
                throw new Exception409("Student with this code already exists");
            }
        }
        return studentConverter.toDTO(studentRepository.save(
                studentConverter.toEntity(dto, studentRepository.findOneById(id))));
    }

    @Override
    public void deleteStudent(Long id) {
        checkExists(id);
        studentRepository.delete(studentRepository.findOneById(id));
    }

    @Override
    public StudentDTO findOneStudent(Long id) {
        checkExists(id);
        return studentConverter.toDTO(studentRepository.findOneById(id));
    }

    @Override
    public List<StudentDTO> findAllStudent() {
        return studentRepository.findAll().stream()
                .map(studentConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ByteArrayInputStream getActualData() {
        List<StudentEntity> all = studentRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = helper.dataToExcel(all);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteArrayInputStream;
    }

    @Override
    public void importStudentFromExcelFile(MultipartFile file) {
        try {
            List<StudentEntity> list = helper.convertFileExcelToListStudent(file.getInputStream());
            studentRepository.saveAll(list);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void checkExists(Long id) {
        if (studentRepository.findOneById(id) == null) {
            throw new Exception404("Student not found with this id");
        }
    }
}
