package com.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String name;

    public DepartmentDTO(String name) {
        this.name = name;
    }

    public DepartmentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    //    private Set<TeacherDTO> teachers;
//    private Set<StudentDTO> students;
}
