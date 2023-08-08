package com.elearning.repository.sub;

import com.elearning.entity.sub.EnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollRepository extends JpaRepository<EnrollEntity, Long> {
    EnrollEntity findOneById(Long id);
}
