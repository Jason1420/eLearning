package com.elearning.service;

import com.elearning.dto.TeacherDTO;
import com.elearning.entity.TeacherEntity;

import java.util.List;

public interface TeacherService {
    String updateTeacher(Long id, TeacherDTO dto);

    String deleteTeacher(Long id);

    TeacherEntity showTeacher(Long id);

    List<TeacherEntity> showAllTeacher();
}
