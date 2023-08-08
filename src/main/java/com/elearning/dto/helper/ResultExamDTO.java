package com.elearning.dto.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultExamDTO {
    private Long examId;
    private Long studentId;
    private double score;
}
