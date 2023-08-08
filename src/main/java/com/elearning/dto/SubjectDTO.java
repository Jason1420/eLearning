package com.elearning.dto;

import com.elearning.dto.sub.ClassDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private Long id;
    private String name;
    private String code;
    private int credit;

    public SubjectDTO(Long id, String name, String code, int credit) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.credit = credit;
    }
//    private Set<ClassDTO> classes;
}
