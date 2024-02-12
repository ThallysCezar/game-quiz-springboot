package com.mjv.gamequiz.dtos;

import lombok.Data;

@Data
public class QuizGameDTO {

    private Long questionId;
    private Long chosenAlternativeId;
    private String nickName;

}