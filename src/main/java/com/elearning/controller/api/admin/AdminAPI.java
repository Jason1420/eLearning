package com.elearning.controller.api.admin;

import com.elearning.dto.DepartmentDTO;
import com.elearning.dto.SubjectDTO;
import com.elearning.entity.DepartmentEntity;
import com.elearning.entity.SubjectEntity;
import com.elearning.service.DepartmentService;
import com.elearning.service.SubjectService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminAPI {
    private final DepartmentService departmentService;
    private final SubjectService subjectService;

    /* CRUD Department */
    @PostMapping("/department")
    public String addDepartment(@RequestBody DepartmentDTO dto){
        return departmentService.addDepartment(dto);
    }
    @PutMapping("/department/{id}")
    public String updateDepartment(@PathVariable Long id, @RequestBody String name){
        return departmentService.updateDepartment(id, name);
    }
    @DeleteMapping("/department/{id}")
    public String deleteDepartment(@PathVariable Long id){
        return departmentService.deleteDepartment(id);
    }
    @GetMapping("/department/{id}")
    public DepartmentEntity showDepartment(@PathVariable Long id){
        return departmentService.showDepartment(id);
    }
    @GetMapping("/department")
    public List<DepartmentEntity> showAllDepartment(){
        return departmentService.showAllDepartment();
    }
    /* CRUD Subject */
    @PostMapping("/subject")
    public String addSubject(@RequestBody SubjectDTO dto){
        return subjectService.addSubject(dto);
    }
    @PutMapping("/subject/{id}")
    public String updateSubject(@PathVariable Long id, @RequestBody String name){
        return subjectService.updateSubject(id, name);
    }
    @DeleteMapping("/subject/{id}")
    public String deleteSubject(@PathVariable Long id){
        return subjectService.deleteSubject(id);
    }
    @GetMapping("/subject/{id}")
    public SubjectEntity showSubject(@PathVariable Long id){
        return subjectService.showSubject(id);
    }
    @GetMapping("/subject")
    public List<SubjectEntity> showAllSubject(){
        return subjectService.showAllSubject();
    }
}
