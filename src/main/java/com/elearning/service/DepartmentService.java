package com.elearning.service;

import com.elearning.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO addDepartment(DepartmentDTO dto);

    DepartmentDTO updateDepartment(Long id, DepartmentDTO dto);

    void deleteDepartment(Long id);

    DepartmentDTO findOneDepartment(Long id);

    List<DepartmentDTO> findAllDepartment();
}
