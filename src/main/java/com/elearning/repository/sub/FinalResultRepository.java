package com.elearning.repository.sub;

import com.elearning.entity.sub.FinalResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FinalResultRepository extends JpaRepository<FinalResultEntity, Long> {

    @Query("SELECT f FROM FinalResultEntity f " +
            "WHERE f.student.id = ?1 AND f.clas.id = ?2")
    FinalResultEntity findByStudentIdAndClassId(Long studentId, Long classId);
}
