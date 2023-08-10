package com.elearning.dto.sub;

import com.elearning.dto.SubjectDTO;
import com.elearning.dto.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassDTO {
    private Long id;
    private String name;
    private SubjectDTO subject;
    private TeacherDTO teacher;
}
