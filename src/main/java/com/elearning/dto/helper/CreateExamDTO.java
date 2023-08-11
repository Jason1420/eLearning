package com.elearning.dto.helper;

import com.elearning.helper.ExamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExamDTO {
    private String name;
    private ExamType type;
    private Long classID;
    private Integer coefficient;
}
