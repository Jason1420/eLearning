package com.elearning.controller.api;

import com.elearning.dto.DepartmentDTO;
import com.elearning.dto.StudentDTO;
import com.elearning.entity.DepartmentEntity;
import com.elearning.entity.StudentEntity;
import com.elearning.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentAPI {
    private final StudentService studentService;
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto){
        return studentService.updateStudent(id, dto);
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        return studentService.deleteStudent(id);
    }
    @GetMapping("/{id}")
    public StudentEntity showStudent(@PathVariable Long id){
        return studentService.showStudent(id);
    }
    @GetMapping()
    public List<StudentEntity> showAllStudent(){
        return studentService.showAllStudent();
    }
}
