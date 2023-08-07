package com.elearning.converter;

import com.elearning.dto.DepartmentDTO;
import com.elearning.dto.SubjectDTO;
import com.elearning.entity.DepartmentEntity;
import com.elearning.entity.SubjectEntity;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter {
    public SubjectDTO toDTO(SubjectEntity entity){
        SubjectDTO dto = new SubjectDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setCredit(entity.getCredit());
        return dto;
    }
    public SubjectEntity toEntity(SubjectDTO dto){
        SubjectEntity entity = new SubjectEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setCredit(dto.getCredit());
        return entity;
    }
}
