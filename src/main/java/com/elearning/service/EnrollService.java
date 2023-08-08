package com.elearning.service;

import com.elearning.dto.helper.EnrollClassDTO;
import com.elearning.entity.sub.EnrollEntity;

import java.util.List;

public interface EnrollService {
    String enrollClass(EnrollClassDTO dto);

    String deleteEnrolledClass(Long id);

    List<EnrollEntity> showAllEnrolledClass();
}
