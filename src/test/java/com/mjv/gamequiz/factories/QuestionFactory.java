package com.mjv.gamequiz.factories;

import com.mjv.gamequiz.domains.QuestionChoices;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.domains.Theme;
import com.mjv.gamequiz.dtos.QuestionChoicesDTO;
import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.dtos.QuestionWithoutResponseDTO;
import com.mjv.gamequiz.dtos.ThemeDTO;

import java.util.ArrayList;
import java.util.List;

public class QuestionFactory {

    public static Question createValidQuestion() {
        Question question = new Question();
        question.setId(1L);
        final var theme = new Theme();
        theme.setTheme("Cinema");
        question.setTheme(theme);
        question.setAnswer("Sample Question");
        question.setResponse("Sample Response");
        question.setCorrectAlternativeID(1L);

        List<QuestionChoices> alternatives = new ArrayList<>();
        QuestionChoices alternative1 = new QuestionChoices();
        alternative1.setId(1L);
        alternative1.setAlternative("Sample QuestionChoices A");
        alternative1.setItsCorrect(true);
        alternative1.setQuestion(question);
        alternatives.add(alternative1);

        QuestionChoices alternative2 = new QuestionChoices();
        alternative2.setId(2L);
        alternative2.setAlternative("Sample QuestionChoices B");
        alternative2.setItsCorrect(false);
        alternative2.setQuestion(question);
        alternatives.add(alternative2);

        question.setAlternativeList(alternatives);

        return question;
    }

    public static QuestionDTO createValidQuestionDTO() {
        QuestionDTO questionDTO = new QuestionDTO();
        final var theme = new ThemeDTO();
        theme.setTheme("Cinema");
        questionDTO.setTheme(theme);
        questionDTO.setAnswer("Sample Question");
        questionDTO.setResponse("Sample Response");

        List<QuestionChoicesDTO> alternativeDTOs = new ArrayList<>();
        QuestionChoicesDTO alternativeDTO1 = new QuestionChoicesDTO();
        alternativeDTO1.setAlternative("Sample QuestionChoices A");
        alternativeDTOs.add(alternativeDTO1);

        QuestionChoicesDTO alternativeDTO2 = new QuestionChoicesDTO();
        alternativeDTO2.setAlternative("Sample QuestionChoices B");
        alternativeDTOs.add(alternativeDTO2);

        questionDTO.setAlternativeDTOList(alternativeDTOs);

        return questionDTO;
    }

    public static QuestionWithoutResponseDTO createValidQuestionWithoutResponseDTO() {
        QuestionWithoutResponseDTO questionWithoutResponseDTO = new QuestionWithoutResponseDTO();
        final var theme = new ThemeDTO();
        theme.setTheme("Cinema");
        questionWithoutResponseDTO.setTheme(theme);
        questionWithoutResponseDTO.setAnswer("Sample Question");
        questionWithoutResponseDTO.setResponse("Sample Response");

        List<QuestionChoicesDTO> alternativeDTOs = new ArrayList<>();
        QuestionChoicesDTO alternativeDTO1 = new QuestionChoicesDTO();
        alternativeDTO1.setAlternative("Sample QuestionChoices A");
        alternativeDTOs.add(alternativeDTO1);

        QuestionChoicesDTO alternativeDTO2 = new QuestionChoicesDTO();
        alternativeDTO2.setAlternative("Sample QuestionChoices B");
        alternativeDTOs.add(alternativeDTO2);

        questionWithoutResponseDTO.setAlternativeDTOList(alternativeDTOs);

        return questionWithoutResponseDTO;
    }

    public static List<QuestionDTO> createQuestionDTOList(int size) {
        List<QuestionDTO> questions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            questions.add(createQuestionDTO(i));
        }
        return questions;
    }

    public static QuestionDTO createQuestionDTO(int index) {
        QuestionDTO question = new QuestionDTO();
        question.setId((long) index);

        final var theme = new ThemeDTO();
        theme.setTheme("Cinema");
        question.setTheme(theme);
        question.setAlternativeDTOList(createAlternatives());
        return question;
    }

    public static List<QuestionChoicesDTO> createAlternatives() {
        List<QuestionChoicesDTO> alternatives = new ArrayList<>();
        char letter = 'A';
        for (int i = 0; i < 4; i++) {
            QuestionChoicesDTO questionChoicesDTO = new QuestionChoicesDTO();
            questionChoicesDTO.setId((long) i);
            questionChoicesDTO.setContent("QuestionChoices " + letter++);
            alternatives.add(questionChoicesDTO);
        }
        return alternatives;
    }
}
