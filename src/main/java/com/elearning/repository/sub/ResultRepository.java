package com.elearning.repository.sub;

import com.elearning.entity.sub.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<ResultEntity, Long> {
    ResultEntity findOneById(Long id);

    List<ResultEntity> searchByExamId(Long examId);
}
