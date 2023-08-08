package com.elearning.converter;

import com.elearning.dto.sub.ClassDTO;
import com.elearning.entity.sub.ClassEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClassConverter {
    private final SubjectConverter subjectConverter;
    private final TeacherConverter teacherConverter;

    public ClassDTO toDTO(ClassEntity entity) {
        return new ClassDTO((entity.getId() != null) ? entity.getId() : null,
                entity.getName(),
                subjectConverter.toDTO(entity.getSubject()),
                teacherConverter.toDTO(entity.getTeacher()));
    }

    public ClassEntity toEntity(ClassDTO dto) {
        return new ClassEntity(dto.getId(),
                dto.getName(),
                subjectConverter.toEntity(dto.getSubject()),
                teacherConverter.toEntity(dto.getTeacher()));
    }
}
