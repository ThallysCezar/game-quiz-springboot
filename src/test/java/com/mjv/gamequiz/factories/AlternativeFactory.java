package com.mjv.gamequiz.factories;

import com.mjv.gamequiz.domains.Alternative;
import com.mjv.gamequiz.dtos.AlternativeDTO;

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
}
