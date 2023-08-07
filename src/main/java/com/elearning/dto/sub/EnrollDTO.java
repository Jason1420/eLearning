package com.elearning.dto.sub;

import com.elearning.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollDTO {
    private Long id;

    private StudentDTO student;
    private ClassDTO clas;
}
