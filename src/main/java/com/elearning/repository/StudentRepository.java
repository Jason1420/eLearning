package com.elearning.repository;

import com.elearning.entity.DepartmentEntity;
import com.elearning.entity.StudentEntity;
import com.elearning.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
