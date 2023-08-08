package com.elearning.repository.sub;

import com.elearning.entity.sub.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    ClassEntity findOneById(Long id);

    ClassEntity findByName(String name);

    ClassEntity findOneByName(String name);
}
