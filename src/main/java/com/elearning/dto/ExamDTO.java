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

    public ExamDTO(String name, ExamType type) {
        this.name = name;
        this.type = type;
    }

    public ExamDTO(Long id, String name, ExamType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    //    private Set<ResultDTO> results;

}
