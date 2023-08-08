package com.elearning.service.security;

import com.elearning.dto.helper.ChangePasswordDTO;
import com.elearning.dto.login.UserDTO;
import com.elearning.entity.login.UserEntity;

import java.util.List;

public interface AccountService {


    String createStudentAccount(UserDTO dto);

    String createTeacherAccount(UserDTO dto);

    List<UserEntity> showAllUser();

    UserEntity showUser(Long id);

    String changePassword(Long id, ChangePasswordDTO dto);

    String deleteUser(Long id);

    String addNewRole(String role);
}
