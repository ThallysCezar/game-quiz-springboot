package com.mjv.gamequiz.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private Long id;
    private ThemeDTO theme;
    private String answer;
    private String response;
    private Long correctAlternativeID;
    private List<AlternativeDTO> alternativeDTOList;

    @Override
    public String toString() {
        return "\n\nQuestionDTO:" +
                "\nid = " + id +
                "\ntheme = " + theme +
                "\nanswer = " + answer +
                "\nresponse = " + response +
                "\ncorrectAlternativeID = " + correctAlternativeID +
                "\nalternativeDTOList = " + alternativeDTOList;
    }
}
