package com.elearning.service.security;

import com.elearning.config.PasswordGenerator;
import com.elearning.converter.UserConverter;
import com.elearning.dto.helper.ChangePasswordDTO;
import com.elearning.dto.login.RoleDTO;
import com.elearning.dto.login.UserDTO;
import com.elearning.entity.login.RoleEntity;
import com.elearning.entity.login.UserEntity;
import com.elearning.exception.Exception400;
import com.elearning.exception.Exception404;
import com.elearning.exception.Exception409;
import com.elearning.repository.StudentRepository;
import com.elearning.repository.TeacherRepository;
import com.elearning.repository.security.RoleRepository;
import com.elearning.repository.security.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    //    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;

    @Override
    public UserDTO createStudentAccount(UserDTO dto) {
        if (userRepository.findOneByUsername(dto.getUsername()) != null) {
            throw new Exception409("This username already exists!");
        }
        if (studentRepository.findOneByCode(dto.getStudent().getCode()) != null) {
            throw new Exception409("This student code already exists!");
        }
        UserEntity entity = userConverter.toEntity(dto);
        entity.setPassword(PasswordGenerator.generateRandomPassword(10));
        entity.setRoles(roleRepository.findOneByName("STUDENT"));
        studentRepository.save(entity.getStudent());
        UserEntity savedEntity = userRepository.save(entity);
        return userConverter.toDTO(savedEntity);
    }

    @Override
    public UserDTO createTeacherAccount(UserDTO dto) {
        if (userRepository.findOneByUsername(dto.getUsername()) != null) {
            throw new Exception409("This username already exists!");
        }
        UserEntity entity = userConverter.toEntity(dto);
        entity.setPassword(PasswordGenerator.generateRandomPassword(10));
        entity.setRoles(roleRepository.findOneByName("TEACHER"));
        teacherRepository.save(entity.getTeacher());
        UserEntity savedEntity = userRepository.save(entity);
        return userConverter.toDTO(savedEntity);
    }

    @Override
    public void changePassword(Long id, ChangePasswordDTO dto) {
        UserEntity entity = userRepository.findOneById(id);
        if (entity == null) {
            throw new EntityNotFoundException("This user is not found!");
        }
        if (!userConverter.checkPassword(entity, dto.getCurrentPassword())) {
            throw new Exception400("Wrong current password!");
        }
        entity.setPassword(dto.getNewPassword());
        entity.setChangedPassword(true);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(userRepository.findOneById(id));
    }

    @Override
    public RoleDTO addNewRole(String role) {
        if (roleRepository.findByName(role) != null) {
            throw new Exception409("This role already exist");
        }
        RoleEntity savedEntity = roleRepository.save(new RoleEntity(role));
        return new RoleDTO(savedEntity.getId(), role);
    }

    @Override
    public UserDTO findOneUser(Long id) {
        if (userRepository.findOneById(id) == null) {
            throw new Exception404("User not found with this id");
        }
        return userConverter.toDTO(userRepository.findOneById(id));
    }

    @Override
    public List<UserDTO> findAllUser() {
        List<UserEntity> listEntity = userRepository.findAll();
        return listEntity.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
    }


}
