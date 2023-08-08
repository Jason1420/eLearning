package com.elearning.converter;

import com.elearning.dto.DepartmentDTO;
import com.elearning.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter {
    public DepartmentDTO toDTO(DepartmentEntity entity) {
        return new DepartmentDTO((entity.getId() != null) ? entity.getId() : null,
                entity.getName());
    }

    public DepartmentEntity toEntity(DepartmentDTO dto) {
        return new DepartmentEntity(dto.getId(),
                dto.getName());
    }
}
