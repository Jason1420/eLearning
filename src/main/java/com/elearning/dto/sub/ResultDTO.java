package com.elearning.dto.sub;

import com.elearning.dto.ExamDTO;
import com.elearning.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    private Long id;
    private StudentDTO student;
    private ExamDTO exam;
    private double score;
}
