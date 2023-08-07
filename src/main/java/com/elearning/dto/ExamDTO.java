package com.elearning.dto;

import com.elearning.dto.sub.ResultDTO;
import com.elearning.helper.ExamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {
    private Long id;
    private String name;
    private ExamType type;
    private Set<ResultDTO> results;
}
