package com.elearning.converter;

import com.elearning.dto.sub.FinalResultDTO;
import com.elearning.entity.sub.FinalResultEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FinalResultConverter {
    private final ClassConverter classConverter;
    private final StudentConverter studentConverter;

    public FinalResultDTO toDTO(FinalResultEntity entity) {
        return new FinalResultDTO((entity.getId() != null) ? entity.getId() : null,
                studentConverter.toDTO(entity.getStudent()),
                classConverter.toDTO(entity.getClas()),
                entity.getCredit(),
                entity.getScore());
    }

    public FinalResultEntity toEntity(FinalResultDTO dto) {
        return new FinalResultEntity(dto.getId(),
                studentConverter.toEntity(dto.getStudent()),
                classConverter.toEntity(dto.getClas()),
                dto.getCredit(),
                dto.getScore());
    }
}
