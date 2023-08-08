package com.elearning.service.security;

import com.elearning.config.PasswordGenerator;
import com.elearning.converter.UserConverter;
import com.elearning.dto.helper.ChangePasswordDTO;
import com.elearning.dto.login.UserDTO;
import com.elearning.entity.login.RoleEntity;
import com.elearning.entity.login.UserEntity;
import com.elearning.repository.StudentRepository;
import com.elearning.repository.TeacherRepository;
import com.elearning.repository.security.RoleRepository;
import com.elearning.repository.security.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public String changePassword(Long id, ChangePasswordDTO dto) {
        UserEntity entity = userRepository.findOneById(id);
        if (entity == null) {
            throw new EntityNotFoundException("This user is not found!");
        }
        if (userConverter.checkPassword(entity, dto.getCurrentPassword())) {
            entity.setPassword(dto.getNewPassword());
            entity.setChangedPassword(true);
            return "Password was changed!";
        }
        return "Wrong current password!";
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.delete(userRepository.findOneById(id));
        return "User id = " + id + " has deleted!";
    }

    @Override
    public String addNewRole(String role) {
        RoleEntity entity = roleRepository.findByName(role);
        if (entity != null) {
            return "This role already exist";
        }
        RoleEntity NewEntity = new RoleEntity(role);
        roleRepository.save(NewEntity);
        return "role name " + role + " was added";
    }

    @Override
    public UserEntity showUser(Long id) {
        return userRepository.findOneById(id);
    }

    @Override
    public List<UserEntity> showAllUser() {
        return userRepository.findAll();
    }


}
