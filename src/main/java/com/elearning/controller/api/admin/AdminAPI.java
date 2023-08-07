package com.elearning.controller.api.admin;

import com.elearning.dto.DepartmentDTO;
import com.elearning.dto.SubjectDTO;
import com.elearning.dto.sub.helper.CreateClassDTO;
import com.elearning.entity.DepartmentEntity;
import com.elearning.entity.SubjectEntity;
import com.elearning.entity.sub.ClassEntity;
import com.elearning.service.ClassService;
import com.elearning.service.DepartmentService;
import com.elearning.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminAPI {
    private final DepartmentService departmentService;
    private final SubjectService subjectService;
    private final ClassService classService;

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
    public String updateSubject(@PathVariable Long id, @RequestBody SubjectDTO dto){
        return subjectService.updateSubject(id, dto);
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
    /* CRUD Class */
    @PostMapping("/class")
    public String addClass(@RequestBody CreateClassDTO dto){
        return classService.addClass(dto);
    }
    @PutMapping("/class/{id}")
    public String updateClass(@PathVariable Long id, @RequestBody CreateClassDTO dto){
        return classService.updateClass(id, dto);
    }
    @DeleteMapping("/class/{id}")
    public String deleteClass(@PathVariable Long id){
        return classService.deleteClass(id);
    }
    @GetMapping("/class/{id}")
    public ClassEntity showClass(@PathVariable Long id){
        return classService.showClass(id);
    }
    @GetMapping("/class")
    public List<ClassEntity> showAllClass(){
        return classService.showAllClass();
    }
}
