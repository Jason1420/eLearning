package com.elearning.repository;

import com.elearning.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    DepartmentEntity findOneById(Long id);

    DepartmentEntity findByName(String name);

    DepartmentEntity findOneByName(String department);

}
