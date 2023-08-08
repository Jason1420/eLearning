package com.elearning.service;

import com.elearning.dto.helper.CreateClassDTO;
import com.elearning.entity.sub.ClassEntity;

import java.util.List;

public interface ClassService {
    String addClass(CreateClassDTO dto);

    String updateClass(Long id, CreateClassDTO dto);

    String deleteClass(Long id);

    ClassEntity showClass(Long id);

    List<ClassEntity> showAllClass();
}
