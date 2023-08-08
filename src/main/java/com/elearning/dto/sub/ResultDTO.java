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
    private ExamDTO exam;
    private StudentDTO student;
    private double score;
}
