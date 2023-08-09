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

    public void setCredit(int credit) {
        if (credit > 12) {
            this.credit = 12;
        } else if (credit < 0) {
            this.credit = 0;
        } else {
            this.credit = credit;
        }
    }
//    private Set<ClassDTO> classes;
}
