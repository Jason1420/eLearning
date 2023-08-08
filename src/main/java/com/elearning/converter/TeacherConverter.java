package com.elearning.converter;

import com.elearning.dto.TeacherDTO;
import com.elearning.entity.TeacherEntity;
import com.elearning.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeacherConverter {
    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;

    public TeacherDTO toDTO(TeacherEntity entity) {
        TeacherDTO dto = new TeacherDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setGender(entity.getGender());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        if (entity.getDepartment() != null) {
            dto.setDepartment(departmentConverter.toDTO(entity.getDepartment()));
        }
        return dto;
    }

    public TeacherEntity toEntity(TeacherDTO dto) {
        TeacherEntity entity = new TeacherEntity();
        entity.setFirstName(entity.getFirstName());
        entity.setLastName(entity.getLastName());
        entity.setDateOfBirth(entity.getDateOfBirth());
        entity.setGender(entity.getGender());
        entity.setEmail(entity.getEmail());
        entity.setPhoneNumber(entity.getPhoneNumber());
        if (dto.getDepartment() != null) {
            entity.setDepartment(departmentConverter.toEntity(dto.getDepartment()));
        }
        return entity;
    }

    public TeacherEntity toEntity(TeacherDTO dto, TeacherEntity oldEntity) {
        oldEntity.setFirstName(dto.getFirstName());
        oldEntity.setLastName(dto.getLastName());
        oldEntity.setDateOfBirth(dto.getDateOfBirth());
        oldEntity.setGender(dto.getGender());
        oldEntity.setEmail(dto.getEmail());
        oldEntity.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getDepartment() != null) {
            oldEntity.setDepartment(departmentRepository.findOneByName(dto.getDepartment().getName()));
        }
        return oldEntity;
    }
}
