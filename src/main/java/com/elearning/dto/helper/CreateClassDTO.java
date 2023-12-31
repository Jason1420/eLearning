package com.elearning.dto.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClassDTO {
    private String className;
    private Long subjectId;
    private Long teacherId;
}
