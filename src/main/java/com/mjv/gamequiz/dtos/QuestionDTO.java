package com.mjv.gamequiz.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private Long id;
    private ThemeDTO theme;
    private String answer;
    private String response;
    private Long correctAlternativeID;
    private List<QuestionChoicesDTO> alternativeDTOList;

}