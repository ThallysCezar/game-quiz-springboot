package com.mjv.gamequiz.factories;

import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.domains.QuestionAlternative;
import com.mjv.gamequiz.dtos.QuestionAlternativeDTO;
import com.mjv.gamequiz.dtos.QuestionDTO;

import java.util.ArrayList;
import java.util.List;

public class QuestionFactory {

    public static Question createValidQuestion() {
        Question question = new Question();
        question.setId(1L); // Defina um ID válido para a questão
        question.setTheme("Sample Theme");
        question.setQuestion("Sample Question");
        question.setResponse("Sample Response");
        question.setCorrectQuestionAlternativeID(1L); // Defina um ID válido para a alternativa correta

        // Adicione alternativas de questão válidas
        List<QuestionAlternative> alternatives = new ArrayList<>();
        QuestionAlternative alternative1 = new QuestionAlternative();
        alternative1.setId(1L); // Defina um ID válido para a alternativa
        alternative1.setAlternative("Sample Alternative 1");
        alternative1.setItsCorrect(true); // Marque como correta ou incorreta conforme necessário
        alternative1.setQuestion(question);
        alternatives.add(alternative1);

        QuestionAlternative alternative2 = new QuestionAlternative();
        alternative2.setId(2L);
        alternative2.setAlternative("Sample Alternative 2");
        alternative2.setItsCorrect(false);
        alternative2.setQuestion(question);
        alternatives.add(alternative2);

        question.setQuestionAlternativeList(alternatives);

        return question;
    }

    public static QuestionDTO createValidQuestionDTO() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setTheme("Sample Theme");
        questionDTO.setQuestion("Sample Question");
        questionDTO.setResponse("Sample Response");

        // Adicione alternativas de questão válidas
        List<QuestionAlternativeDTO> alternativeDTOs = new ArrayList<>();
        QuestionAlternativeDTO alternativeDTO1 = new QuestionAlternativeDTO();
        alternativeDTO1.setAlternative("Sample Alternative 1");
        alternativeDTOs.add(alternativeDTO1);

        QuestionAlternativeDTO alternativeDTO2 = new QuestionAlternativeDTO();
        alternativeDTO2.setAlternative("Sample Alternative 2");
        alternativeDTOs.add(alternativeDTO2);

        questionDTO.setQuestionAlternativeDTOList(alternativeDTOs);

        return questionDTO;
    }
}
