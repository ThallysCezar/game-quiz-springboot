package com.mjv.gamequiz.factories;

import com.mjv.gamequiz.dtos.QuizGameDTO;

public class QuizFactory {

    public static QuizGameDTO createQuizGameDTO() {
        QuizGameDTO quizGameDTO = new QuizGameDTO();
        quizGameDTO.setQuestionId(1L);
        quizGameDTO.setChosenAlternativeId(1L);
        quizGameDTO.setNickName("playerName");
        return quizGameDTO;
    }
}
