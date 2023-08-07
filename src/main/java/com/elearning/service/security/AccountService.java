package com.elearning.service.security;

import com.elearning.dto.login.UserDTO;

public interface AccountService {


    String createStudentAccount(UserDTO dto);

    String createTeacherAccount(UserDTO dto);
}
