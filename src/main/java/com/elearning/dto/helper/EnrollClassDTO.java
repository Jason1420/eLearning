package com.elearning.dto.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollClassDTO {
    private Long studentId;
    private Long classId;
}
