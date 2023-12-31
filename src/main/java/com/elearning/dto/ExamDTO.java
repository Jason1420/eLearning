package com.elearning.dto;

import com.elearning.dto.sub.ClassDTO;
import com.elearning.helper.ExamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {
    private Long id;
    private String name;
    private ExamType type;
    private ClassDTO clas;
    private Integer coefficient;
}
