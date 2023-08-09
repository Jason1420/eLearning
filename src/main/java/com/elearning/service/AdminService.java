package com.elearning.service;

import java.util.List;

public interface AdminService {

    List<List<Object>> updateFinalResult(Long id);

    List<List<Object>> findAllResult();

    void updateResultToStudent();
}
