package com.elearning.controller.api.crud;

import com.elearning.dto.DepartmentDTO;
import com.elearning.exception.helper.Result;
import com.elearning.exception.helper.StatusCode;
import com.elearning.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@AllArgsConstructor
public class DepartmentAPI {
    private final DepartmentService departmentService;

    /* CRUD Department */
    @PostMapping
    public Result addDepartment(@RequestBody DepartmentDTO dto) {
        DepartmentDTO savedDepartment = departmentService.addDepartment(dto);
        return new Result(true, StatusCode.SUCCESS, "Add success", savedDepartment);
    }

    @PutMapping("/{id}")
    public Result updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO dto) {
        DepartmentDTO savedDepartment = departmentService.updateDepartment(id, dto);
        return new Result(true, StatusCode.SUCCESS, "Update success", savedDepartment);
    }

    @DeleteMapping("/{id}")
    public Result deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return new Result(true, StatusCode.SUCCESS, "Delete success");
    }

    @GetMapping("/{id}")
    public Result findOneDepartment(@PathVariable Long id) {
        DepartmentDTO dto = departmentService.findOneDepartment(id);
        return new Result(true, StatusCode.SUCCESS, "Find one success", dto);
    }

    @GetMapping
    public Result findAllDepartment() {
        List<DepartmentDTO> listDTO = departmentService.findAllDepartment();
        return new Result(true, StatusCode.SUCCESS, "Find one success", listDTO);
    }
}
