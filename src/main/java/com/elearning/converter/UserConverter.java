package com.elearning.converter;

import com.elearning.dto.login.UserDTO;
import com.elearning.entity.StudentEntity;
import com.elearning.entity.TeacherEntity;
import com.elearning.entity.login.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDTO toDTO(UserEntity entity) {
        return new UserDTO((entity.getId() != null) ? entity.getId() : null,
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail());
    }

    public UserEntity toEntity(UserDTO dto) {
        return new UserEntity(dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                (dto.getStudent() != null) ?
                        new StudentEntity(dto.getStudent().getCode(), dto.getStudent().getFirstName(),
                                dto.getStudent().getLastName()) : null,
                (dto.getTeacher() != null) ?
                        new TeacherEntity(dto.getTeacher().getFirstName(),
                                dto.getTeacher().getLastName()) : null);
    }

    public UserEntity toEntity(UserDTO dto, UserEntity oldEntity) {
        oldEntity.setUsername(dto.getUsername());
        oldEntity.setPassword(dto.getPassword());
        oldEntity.setEmail(dto.getEmail());
        return oldEntity;
    }

    public boolean checkPassword(UserEntity entity, String currentPassword) {
        return entity.getPassword().equals(currentPassword);
    }
}
