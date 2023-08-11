package com.elearning.repository.sub;

import com.elearning.entity.sub.EnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollRepository extends JpaRepository<EnrollEntity, Long> {
    EnrollEntity findOneById(Long id);

    @Query("SELECT ee.student.id, ee.clas.id,r.exam.id, ex.type, r.score, s.credit " +
            "FROM EnrollEntity ee " +
            "INNER JOIN ExamEntity ex " +
            "ON ee.clas.id = ex.clas.id " +
            "INNER JOIN ResultEntity r " +
            "ON ex.id = r.exam.id " +
            "INNER JOIN ClassEntity c " +
            "ON ee.clas.id = c.id " +
            "INNER JOIN SubjectEntity s " +
            "ON c.subject.id = s.id")
    List<List<Object>> findResult();

    @Query("SELECT e FROM EnrollEntity e " +
            "WHERE e.student.id = ?1 AND e.clas.id = ?2")
    EnrollEntity findOneByStudentIdAndClassId(Long id, Long classID);
}
