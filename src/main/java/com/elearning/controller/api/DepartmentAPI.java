package com.elearning.controller.api;

import com.elearning.dto.DepartmentDTO;
import com.elearning.entity.DepartmentEntity;
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
    @PostMapping("")
    public String addDepartment(@RequestBody DepartmentDTO dto){
        return departmentService.addDepartment(dto);
    }
    @PutMapping("/{id}")
    public String updateDepartment(@PathVariable Long id, @RequestBody String name){
        return departmentService.updateDepartment(id, name);
    }
    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id){
        return departmentService.deleteDepartment(id);
    }
    @GetMapping("/{id}")
    public DepartmentEntity showDepartment(@PathVariable Long id){
        return departmentService.showDepartment(id);
    }
    @GetMapping()
    public List<DepartmentEntity> showAllDepartment(){
        return departmentService.showAllDepartment();
    }
}
