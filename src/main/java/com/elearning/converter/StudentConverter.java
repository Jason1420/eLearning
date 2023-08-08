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
        return new StudentDTO((entity.getId() != null) ? entity.getId() : null,
                entity.getCode(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDateOfBirth(),
                entity.getGender(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                (entity.getDepartment() != null) ?
                        departmentConverter.toDTO(entity.getDepartment()) : null);
    }

    public StudentEntity toEntity(StudentDTO dto) {
        return new StudentEntity(dto.getCode(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getDateOfBirth(),
                dto.getGender(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getAddress(),
                (dto.getDepartment() != null) ?
                        departmentRepository.findOneByName(dto.getDepartment().getName()) : null);
    }

    public StudentEntity toEntity(StudentDTO dto, StudentEntity oldEntity) {
        return new StudentEntity(oldEntity.getId(),
                dto.getCode(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getDateOfBirth(),
                dto.getGender(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getAddress(),
                (dto.getDepartment() != null) ?
                        departmentRepository.findOneByName(dto.getDepartment().getName()) : null);
    }
}
