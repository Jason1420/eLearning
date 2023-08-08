package com.elearning.repository;

import com.elearning.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    SubjectEntity findOneById(Long id);

    SubjectEntity findOneByName(String name);

    SubjectEntity findOneByCode(String code);
}
