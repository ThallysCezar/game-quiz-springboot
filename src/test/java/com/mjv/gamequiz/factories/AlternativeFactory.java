package com.mjv.gamequiz.factories;

import com.mjv.gamequiz.domains.QuestionChoices;
import com.mjv.gamequiz.dtos.QuestionChoicesDTO;

import java.util.ArrayList;
import java.util.List;

public class AlternativeFactory {

    public static QuestionChoices createValidAlternative() {
        QuestionChoices alternative = new QuestionChoices();
        alternative.setId(1L);
        alternative.setAlternative("A");
        alternative.setItsCorrect(true);
        alternative.setContent("This is the content for alternative A");
        return alternative;
    }

    public static QuestionChoicesDTO createValidAlternativeDTO() {
        QuestionChoicesDTO alternativeDTO = new QuestionChoicesDTO();
        alternativeDTO.setId(1L);
        alternativeDTO.setAlternative("A");
        alternativeDTO.setContent("This is the content for alternative A");
        return alternativeDTO;
    }

    public static List<QuestionChoicesDTO> createAlternativeDTOList(int size) {
        List<QuestionChoicesDTO> alternatives = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            alternatives.add(createAlternativeDTO(i));
        }
        return alternatives;
    }

    public static QuestionChoicesDTO createAlternativeDTO(int index) {
        QuestionChoicesDTO alternative = new QuestionChoicesDTO();
        alternative.setId((long) index);
        alternative.setContent("QuestionChoices " + (char) ('A' + index));
        alternative.setAlternative("A" + (char) ('A' + index));
        return alternative;
    }

}
