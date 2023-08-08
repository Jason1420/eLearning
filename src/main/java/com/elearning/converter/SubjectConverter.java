package com.elearning.converter;

import com.elearning.dto.SubjectDTO;
import com.elearning.entity.SubjectEntity;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter {
    public SubjectDTO toDTO(SubjectEntity entity) {
        return new SubjectDTO(entity.getId(),
                entity.getName(),
                entity.getCode(),
                entity.getCredit());
    }

    public SubjectEntity toEntity(SubjectDTO dto) {
        return new SubjectEntity(dto.getId(),
                dto.getName(),
                dto.getCode(),
                dto.getCredit());
    }
}
