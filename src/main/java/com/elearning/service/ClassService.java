package com.elearning.service;

import com.elearning.dto.helper.CreateClassDTO;
import com.elearning.dto.sub.ClassDTO;

import java.util.List;

public interface ClassService {
    ClassDTO addClass(CreateClassDTO dto);

    ClassDTO updateClass(Long id, CreateClassDTO dto);

    void deleteClass(Long id);

    ClassDTO findOneClass(Long id);

    List<ClassDTO> findAllClass();
}
