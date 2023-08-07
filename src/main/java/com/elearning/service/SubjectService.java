package com.elearning.service;

import com.elearning.dto.SubjectDTO;
import com.elearning.entity.SubjectEntity;

import java.util.List;

public interface SubjectService {

    String addSubject(SubjectDTO dto);

    String updateSubject(Long id, SubjectDTO dto);

    String deleteSubject(Long id);

    SubjectEntity showSubject(Long id);

    List<SubjectEntity> showAllSubject();
}
