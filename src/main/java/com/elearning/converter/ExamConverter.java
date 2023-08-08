package com.elearning.converter;

import com.elearning.dto.ExamDTO;
import com.elearning.entity.ExamEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExamConverter {
    private final ClassConverter classConverter;

    public ExamDTO toDTO(ExamEntity entity) {
        return new ExamDTO((entity.getId() != null) ? entity.getId() : null,
                entity.getName(),
                entity.getType(),
                classConverter.toDTO(entity.getClas()));
    }

    public ExamEntity toEntity(ExamDTO dto) {
        return new ExamEntity(dto.getName(),
                dto.getType());
    }

    public ExamEntity toEntity(ExamDTO dto, ExamEntity oldEntity) {
        oldEntity.setName(dto.getName());
        oldEntity.setType(dto.getType());
        return oldEntity;
    }
}
