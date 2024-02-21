package com.mjv.gamequiz.factories;

import com.mjv.gamequiz.domains.Alternative;
import com.mjv.gamequiz.dtos.AlternativeDTO;

import java.util.ArrayList;
import java.util.List;

public class AlternativeFactory {

    public static Alternative createValidAlternative() {
        Alternative alternative = new Alternative();
        alternative.setId(1L);
        alternative.setAlternative("A");
        alternative.setItsCorrect(true);
        alternative.setContent("This is the content for alternative A");
        return alternative;
    }

    public static AlternativeDTO createValidAlternativeDTO() {
        AlternativeDTO alternativeDTO = new AlternativeDTO();
        alternativeDTO.setId(1L);
        alternativeDTO.setAlternative("A");
        alternativeDTO.setContent("This is the content for alternative A");
        return alternativeDTO;
    }



    public static List<AlternativeDTO> createAlternativeDTOList(int size) {
        List<AlternativeDTO> alternatives = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            alternatives.add(createAlternativeDTO(i));
        }
        return alternatives;
    }

    public static AlternativeDTO createAlternativeDTO(int index) {
        AlternativeDTO alternative = new AlternativeDTO();
        alternative.setId((long) index);
        alternative.setContent("Alternative " + (char) ('A' + index));
        alternative.setAlternative("A" + (char) ('A' + index));
        return alternative;
    }

    public static List<Alternative> createAlternativeList(int size) {
        List<Alternative> alternatives = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            alternatives.add(createAlternative(i));
        }
        return alternatives;
    }

    public static Alternative createAlternative(int index) {
        Alternative alternative = new Alternative();
        alternative.setId((long) index);
        alternative.setAlternative("A" + (char) ('A' + index));
        alternative.setContent("Content " + (char) ('A' + index));
        return alternative;
    }

}
