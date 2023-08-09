package com.elearning.dto.sub;

import com.elearning.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalResultDTO {
    private Long id;
    private StudentDTO student;
    private ClassDTO clas;
    private int credit;
    private double score;

    public void setScore(double score) {
        if (score > 10.0) {
            this.score = 10.0;
        } else if (score < 0) {
            this.score = 0;
        } else {
            this.score = score;
        }
    }
}
