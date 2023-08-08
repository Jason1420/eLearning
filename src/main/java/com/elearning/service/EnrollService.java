package com.elearning.service;

import com.elearning.dto.helper.EnrollClassDTO;
import com.elearning.dto.sub.EnrollDTO;
import com.elearning.entity.sub.EnrollEntity;

import java.util.List;

public interface EnrollService {
    EnrollDTO enrollClass(EnrollClassDTO dto);

    void deleteEnrolledClass(Long id);
    EnrollDTO findOneEnrolledClass(Long id);

    List<EnrollDTO> findAllEnrolledClass();
}
