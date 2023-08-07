package com.elearning.repository;

import com.elearning.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
    ExamEntity findOneById(Long id);
}
