package com.elearning.converter;

import com.elearning.dto.ExamDTO;
import com.elearning.entity.ExamEntity;
import org.springframework.stereotype.Component;

@Component
public class ExamConverter {
    public ExamDTO toDTO(ExamEntity entity){
        ExamDTO dto = new ExamDTO();
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        return dto;
    }
    public ExamEntity toEntity(ExamDTO dto){
        ExamEntity entity = new ExamEntity();
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        return entity;
    }
    public ExamEntity toEntity(ExamDTO dto, ExamEntity oldEntity){
        oldEntity.setName(dto.getName());
        oldEntity.setType(dto.getType());
        return oldEntity;
    }
}
