package com.elearning.converter;

import com.elearning.dto.StudentDTO;
import com.elearning.entity.StudentEntity;
import com.elearning.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentConverter {
    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;

    public StudentDTO toDTO(StudentEntity entity) {
        StudentDTO dto = new StudentDTO();
        dto.setCode(entity.getCode());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setGender(entity.getGender());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAddress(entity.getAddress());
        if (entity.getDepartment() != null) {
            dto.setDepartment(departmentConverter.toDTO(entity.getDepartment()));
        }
        return dto;
    }

    public StudentEntity toEntity(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setCode(entity.getCode());
        entity.setFirstName(entity.getFirstName());
        entity.setLastName(entity.getLastName());
        entity.setDateOfBirth(entity.getDateOfBirth());
        entity.setGender(entity.getGender());
        entity.setEmail(entity.getEmail());
        entity.setPhoneNumber(entity.getPhoneNumber());
        entity.setAddress(entity.getAddress());
        if (dto.getDepartment() != null) {
            entity.setDepartment(departmentConverter.toEntity(dto.getDepartment()));
        }
        return entity;
    }

    public StudentEntity toEntity(StudentDTO dto, StudentEntity oldEntity) {
        oldEntity.setCode(dto.getCode());
        oldEntity.setFirstName(dto.getFirstName());
        oldEntity.setLastName(dto.getLastName());
        oldEntity.setDateOfBirth(dto.getDateOfBirth());
        oldEntity.setGender(dto.getGender());
        oldEntity.setEmail(dto.getEmail());
        oldEntity.setPhoneNumber(dto.getPhoneNumber());
        oldEntity.setAddress(dto.getAddress());
        if (dto.getDepartment() != null) {
            oldEntity.setDepartment(departmentRepository.findOneByName(dto.getDepartment().getName()));
        }
        return oldEntity;
    }
}
