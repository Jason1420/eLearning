package com.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String name;

    public DepartmentDTO(String name) {
        this.name = name;
    }

    //    private Set<TeacherDTO> teachers;
//    private Set<StudentDTO> students;
}
