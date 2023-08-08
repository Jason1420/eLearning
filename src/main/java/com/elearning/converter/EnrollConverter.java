package com.elearning.converter;

import com.elearning.dto.sub.ClassDTO;
import com.elearning.dto.sub.EnrollDTO;
import com.elearning.entity.sub.ClassEntity;
import com.elearning.entity.sub.EnrollEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EnrollConverter {
    private final ClassConverter classConverter;
    private final StudentConverter studentConverter;

    public EnrollDTO toDTO(EnrollEntity entity) {
        return new EnrollDTO((entity.getId() != null) ? entity.getId() : null,
                classConverter.toDTO(entity.getClas()),
                studentConverter.toDTO(entity.getStudent()));
    }

    public EnrollEntity toEntity(EnrollDTO dto) {
        return new EnrollEntity(dto.getId(),
                classConverter.toEntity(dto.getClas()),
                studentConverter.toEntity(dto.getStudent()));
    }
}
