package com.elearning.service.security;

import com.elearning.config.PasswordGenerator;
import com.elearning.converter.UserConverter;
import com.elearning.dto.login.UserDTO;
import com.elearning.entity.login.UserEntity;
import com.elearning.repository.StudentRepository;
import com.elearning.repository.TeacherRepository;
import com.elearning.repository.security.RoleRepository;
import com.elearning.repository.security.UserRepository;
import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public String createStudentAccount(UserDTO dto) {
        UserEntity entity = userConverter.toEntity(dto);
        entity.setPassword(PasswordGenerator.generateRandomPassword(10));
        entity.setRoles(roleRepository.findOneByName("STUDENT"));
        studentRepository.save(entity.getStudent());
        UserEntity savedEntity = userRepository.save(entity);
        return savedEntity.getUsername() + " was registered with password: " + savedEntity.getPassword() +
                "\nyour student id: " + savedEntity.getStudent().getId() +
                ", please update your information and change your password!";
    }

    @Override
    public String createTeacherAccount(UserDTO dto) {
        UserEntity entity = userConverter.toEntity(dto);
        entity.setPassword(PasswordGenerator.generateRandomPassword(10));
        entity.setRoles(roleRepository.findOneByName("TEACHER"));
        teacherRepository.save(entity.getTeacher());
        UserEntity savedEntity = userRepository.save(entity);
        return savedEntity.getUsername() + " was registered with password: " + savedEntity.getPassword() +
                "\nyour student id: " + savedEntity.getTeacher().getId() +
                ", please update your information and change your password!";
    }
}
