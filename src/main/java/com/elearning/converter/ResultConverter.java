package com.elearning.converter;

import com.elearning.dto.sub.EnrollDTO;
import com.elearning.dto.sub.ResultDTO;
import com.elearning.entity.sub.EnrollEntity;
import com.elearning.entity.sub.ResultEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ResultConverter {
    private final ExamConverter examConverter;
    private final StudentConverter studentConverter;

    public ResultDTO toDTO(ResultEntity entity) {
        return new ResultDTO((entity.getId() != null) ? entity.getId() : null,
                examConverter.toDTO(entity.getExam()),
                studentConverter.toDTO(entity.getStudent()),
                entity.getScore());
    }

    public ResultEntity toEntity(ResultDTO dto) {
        return new ResultEntity(dto.getId(),
                examConverter.toEntity(dto.getExam()),
                studentConverter.toEntity(dto.getStudent()),
                dto.getScore());
    }
}
