package com.elearning.controller.api.crud;

import com.elearning.dto.helper.EnrollClassDTO;
import com.elearning.dto.sub.EnrollDTO;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.service.EnrollService;
import com.elearning.service.security.CustomUserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enroll")
@AllArgsConstructor
public class EnrollAPI {
    private final EnrollService enrollService;
    private final CustomUserDetailServiceImpl customUserDetailService;

    @PostMapping
    public Result enrollClass(@RequestBody EnrollClassDTO dto) {
        if (customUserDetailService.checkUserId(dto.getStudentId())) {
            EnrollDTO savedDTO = enrollService.enrollClass(dto);
            return new Result(true, StatusCode.SUCCESS, "Add success", savedDTO);
        }
        return new Result(false, StatusCode.FORBIDDEN, "No permission");
    }

    @DeleteMapping("/{id}")
    public Result deleteEnrolledClass(@PathVariable Long id) {
        enrollService.deleteEnrolledClass(id);
        return new Result(true, StatusCode.SUCCESS, "Delete success");
    }

    @GetMapping("/{id}")
    public Result findOneEnrolledClass(@PathVariable("id") Long id) {
        EnrollDTO dto = enrollService.findOneEnrolledClass(id);
        return new Result(true, StatusCode.SUCCESS, "Find one success", dto);
    }

    @GetMapping
    public Result findAllEnrolledClass() {
        List<EnrollDTO> listDTO = enrollService.findAllEnrolledClass();
        return new Result(true, StatusCode.SUCCESS, "Find all success", listDTO);
    }
}
