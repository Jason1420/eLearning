package com.elearning.converter;

import com.elearning.dto.DepartmentDTO;
import com.elearning.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter {
    public DepartmentDTO toDTO(DepartmentEntity entity){
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
    public DepartmentEntity toEntity(DepartmentDTO dto){
        DepartmentEntity entity = new DepartmentEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
