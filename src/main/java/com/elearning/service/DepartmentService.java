package com.elearning.service;

import com.elearning.dto.DepartmentDTO;
import com.elearning.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentService {
    String addDepartment(DepartmentDTO dto);

    String updateDepartment(Long id, String name);

    String deleteDepartment(Long id);

    DepartmentEntity showDepartment(Long id);

    List<DepartmentEntity> showAllDepartment();
}
