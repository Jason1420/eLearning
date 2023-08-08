package com.elearning.service;

import com.elearning.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {
    TeacherDTO updateTeacher(Long id, TeacherDTO dto);

    void deleteTeacher(Long id);

    TeacherDTO findOneTeacher(Long id);

    List<TeacherDTO> findAllTeacher();
}
