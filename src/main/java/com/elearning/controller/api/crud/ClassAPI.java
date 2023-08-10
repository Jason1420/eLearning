package com.elearning.controller.api.crud;

import com.elearning.dto.helper.CreateClassDTO;
import com.elearning.dto.sub.ClassDTO;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.service.ClassService;
import com.elearning.service.security.CustomUserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/class")
@AllArgsConstructor
public class ClassAPI {
    private final ClassService classService;
    private final CustomUserDetailServiceImpl customUserDetailService;
    /* CRUD Class */
    @PostMapping
    public Result addClass(@RequestBody CreateClassDTO dto) {
        if(customUserDetailService.checkUserId(dto.getTeacherId())){
        ClassDTO savedDTO = classService.addClass(dto);
        return new Result(true, StatusCode.SUCCESS, "Add success", savedDTO);
        }
        return new Result(false, StatusCode.FORBIDDEN, "FORBIDDEN");
    }

    @PutMapping("/{id}")
    public Result updateClass(@PathVariable Long id, @RequestBody CreateClassDTO dto) {
        if(customUserDetailService.checkUserId(id)){
        ClassDTO savedDTO = classService.updateClass(id, dto);
        return new Result(true, StatusCode.SUCCESS, "Update success", savedDTO);
        }
        return new Result(false, StatusCode.FORBIDDEN, "FORBIDDEN");
    }

    @DeleteMapping("/{id}")
    public Result deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
        return new Result(true, StatusCode.SUCCESS, "Delete success");
    }

    @GetMapping("/{id}")
    public Result findOneClass(@PathVariable Long id) {
        ClassDTO dto = classService.findOneClass(id);
        return new Result(true, StatusCode.SUCCESS, "Find one success", dto);
    }

    @GetMapping
    public Result findAllClass() {
        List<ClassDTO> listDTO = classService.findAllClass();
        return new Result(true, StatusCode.SUCCESS, "Find all success", listDTO);
    }
}
