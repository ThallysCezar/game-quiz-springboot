package com.mjv.gamequiz.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAlternativeDTO {

    private Long id;
    private String alternative;
    private String reference;

    @Override
    public String toString() {
        return "QuestionAlternative: " +
                "---> id= " + id +
                "---> Reference= " + reference +
                "---> Alternative= " + alternative;
    }

}