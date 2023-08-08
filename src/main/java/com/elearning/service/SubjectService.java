package com.elearning.service;

import com.elearning.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {

    SubjectDTO addSubject(SubjectDTO dto);

    SubjectDTO updateSubject(Long id, SubjectDTO dto);

    void deleteSubject(Long id);

    SubjectDTO findOneSubject(Long id);

    List<SubjectDTO> findAllSubject();
}
