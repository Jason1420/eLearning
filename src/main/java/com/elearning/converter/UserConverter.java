package com.elearning.converter;

import com.elearning.dto.login.UserDTO;
import com.elearning.entity.StudentEntity;
import com.elearning.entity.TeacherEntity;
import com.elearning.entity.login.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDTO toDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    public UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        if(dto.getStudent() != null){
            entity.setStudent(new StudentEntity(dto.getStudent().getCode(),dto.getStudent().getFirstName(),
                    dto.getStudent().getLastName()));
        }
        if(dto.getTeacher() != null){
            entity.setTeacher(new TeacherEntity(dto.getTeacher().getFirstName(),
                    dto.getTeacher().getLastName()));
        }
        return entity;
    }

    public UserEntity toEntity(UserDTO dto, UserEntity oldEntity) {
        oldEntity.setUsername(dto.getUsername());
        oldEntity.setPassword(dto.getPassword());
        oldEntity.setEmail(dto.getEmail());
        return oldEntity;
    }
}
