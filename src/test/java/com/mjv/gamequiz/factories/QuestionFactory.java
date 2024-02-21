package com.mjv.gamequiz.factories;

import com.mjv.gamequiz.domains.Alternative;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.domains.Theme;
import com.mjv.gamequiz.dtos.AlternativeDTO;
import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.dtos.ThemeDTO;

import java.util.ArrayList;
import java.util.List;

public class QuestionFactory {

    public static Question createValidQuestion() {
        Question question = new Question();
        question.setId(1L);
        final var theme = new Theme();
        question.setTheme(theme);
        question.setAnswer("Sample Question");
        question.setResponse("Sample Response");
        question.setCorrectAlternativeID(1L);

        List<Alternative> alternatives = new ArrayList<>();
        Alternative alternative1 = new Alternative();
        alternative1.setId(1L);
        alternative1.setAlternative("Sample Alternative A");
        alternative1.setItsCorrect(true);
        alternative1.setQuestion(question);
        alternatives.add(alternative1);

        Alternative alternative2 = new Alternative();
        alternative2.setId(2L);
        alternative2.setAlternative("Sample Alternative B");
        alternative2.setItsCorrect(false);
        alternative2.setQuestion(question);
        alternatives.add(alternative2);

        question.setAlternativeList(alternatives);

        return question;
    }

    public static QuestionDTO createValidQuestionDTO() {
        QuestionDTO questionDTO = new QuestionDTO();
        final var theme = new ThemeDTO();
        questionDTO.setTheme(theme);
        questionDTO.setAnswer("Sample Question");
        questionDTO.setResponse("Sample Response");

        List<AlternativeDTO> alternativeDTOs = new ArrayList<>();
        AlternativeDTO alternativeDTO1 = new AlternativeDTO();
        alternativeDTO1.setAlternative("Sample Alternative A");
        alternativeDTOs.add(alternativeDTO1);

        AlternativeDTO alternativeDTO2 = new AlternativeDTO();
        alternativeDTO2.setAlternative("Sample Alternative B");
        alternativeDTOs.add(alternativeDTO2);

        questionDTO.setAlternativeDTOList(alternativeDTOs);

        return questionDTO;
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

    public static List<AlternativeDTO> createAlternatives() {
        List<AlternativeDTO> alternatives = new ArrayList<>();
        char letter = 'A';
        for (int i = 0; i < 4; i++) {
            AlternativeDTO alternative = new AlternativeDTO();
            alternative.setId((long) i);
            alternative.setContent("Alternative " + letter++);
            alternatives.add(alternative);
        }
        return alternatives;
    }
}
