package com.elearning.service.security;

import com.elearning.dto.helper.ChangePasswordDTO;
import com.elearning.dto.login.RoleDTO;
import com.elearning.dto.login.UserDTO;
import com.elearning.entity.login.UserEntity;

import java.util.List;

public interface AccountService {


    UserDTO createStudentAccount(UserDTO dto);

    UserDTO createTeacherAccount(UserDTO dto);

    List<UserDTO> findAllUser();

    UserDTO findOneUser(Long id);
//    Long findOneUser(Long id);

    void changePassword(Long id, ChangePasswordDTO dto);

    void deleteUser(Long id);

    RoleDTO addNewRole(String role);

    UserEntity loadUserByUsername(String username);
}
