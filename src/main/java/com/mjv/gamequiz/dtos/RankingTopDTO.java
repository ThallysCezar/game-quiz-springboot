package com.mjv.gamequiz.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RankingTopDTO {

    private String nickName;
    private Integer score;

}