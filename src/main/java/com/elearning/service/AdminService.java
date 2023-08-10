package com.elearning.service;

import com.elearning.dto.login.UserDTO;

import java.util.List;

public interface AdminService {

    List<List<Object>> updateFinalResult(Long id);

    List<List<Object>> findAllResult();

    void updateResultToStudent();

    UserDTO updateUser(Long id, UserDTO userDTO);
}
