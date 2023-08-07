package com.elearning.service;

import com.elearning.dto.DepartmentDTO;
import com.elearning.dto.SubjectDTO;
import com.elearning.entity.DepartmentEntity;
import com.elearning.entity.SubjectEntity;

import java.util.List;

public interface SubjectService {

    String addSubject(SubjectDTO dto);

    String updateSubject(Long id, String name);

    String deleteSubject(Long id);

    SubjectEntity showSubject(Long id);

    List<SubjectEntity> showAllSubject();
}
