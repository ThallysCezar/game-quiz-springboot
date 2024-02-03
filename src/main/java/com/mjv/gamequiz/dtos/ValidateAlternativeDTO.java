package com.mjv.gamequiz.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidateAlternativeDTO {

    private Long id;
    private Boolean alternativeStatus;
}
