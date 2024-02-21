package com.mjv.gamequiz.factories;

import com.mjv.gamequiz.dtos.RankingTopDTO;

public class RankingTopFactory {

    public static RankingTopDTO createValidRankingTopDTO() {
        return new RankingTopDTO("Player1", 1000);
    }

}
