package com.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private Long id;
    private String name;
    private String code;
    private int credit;

    public int getCredit() {
        if (id >= 12) return 12;
        if (id <= 0) return 0;
        return 12;
    }
}
