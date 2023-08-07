package com.elearning.service.security;

import com.elearning.dto.login.UserDTO;
import com.elearning.entity.login.UserEntity;

import java.util.List;

public interface AccountService {


    String createStudentAccount(UserDTO dto);

    String createTeacherAccount(UserDTO dto);

    List<UserEntity> showAllUser();
}
