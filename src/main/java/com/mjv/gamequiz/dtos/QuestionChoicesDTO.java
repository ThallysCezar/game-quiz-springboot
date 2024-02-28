package com.mjv.gamequiz.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionChoicesDTO {

    private Long id;
    private String alternative;
    private String content;

}