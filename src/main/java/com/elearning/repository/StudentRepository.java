package com.elearning.repository;

import com.elearning.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findOneById(Long id);

    StudentEntity findOneByCode(String code);
}
