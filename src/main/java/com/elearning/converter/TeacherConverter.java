package com.elearning.converter;

import com.elearning.dto.TeacherDTO;
import com.elearning.entity.TeacherEntity;
import com.elearning.helper.Gender;
import com.elearning.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@AllArgsConstructor
public class TeacherConverter {
    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;

    public TeacherDTO toDTO(TeacherEntity entity) {
        return new TeacherDTO((entity.getId() != null) ? entity.getId() : null,
                entity.getFirstName(),
                entity.getLastName(),
                (entity.getDateOfBirth() != null) ? entity.getDateOfBirth().toString().substring(0, 10) : null,
                (entity.getGender() != null) ? entity.getGender().toString() : null,
                entity.getEmail(),
                entity.getPhoneNumber(),
                (entity.getDepartment() != null) ?
                        departmentConverter.toDTO(entity.getDepartment()) : null);
    }

    public TeacherEntity toEntity(TeacherDTO dto) {
        return new TeacherEntity(dto.getFirstName(),
                dto.getLastName(),
                Date.valueOf(dto.getDateOfBirth()),
                Gender.valueOf(dto.getGender()),
                dto.getEmail(),
                dto.getPhoneNumber(),
                (dto.getDepartment() != null) ?
                        departmentRepository.findOneByName(dto.getDepartment().getName()) : null);
    }

    public TeacherEntity toEntity(TeacherDTO dto, TeacherEntity oldEntity) {
        return new TeacherEntity(oldEntity.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                Date.valueOf(dto.getDateOfBirth()),
                Gender.valueOf(dto.getGender()),
                dto.getEmail(),
                dto.getPhoneNumber(),
                (dto.getDepartment() != null) ?
                        departmentRepository.findOneByName(dto.getDepartment().getName()) : null);
    }
}
