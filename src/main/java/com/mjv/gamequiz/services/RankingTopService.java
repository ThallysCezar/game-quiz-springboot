package com.mjv.gamequiz.services;

import com.mjv.gamequiz.dtos.RankingTopDTO;
import com.mjv.gamequiz.repositories.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RankingTopService {

    private final PlayerRepository playerRepository;

    public List<RankingTopDTO> findAllByRanking() {
        List<Object[]> results = playerRepository.findAllByRanking();
        List<RankingTopDTO> dtos = new ArrayList<>();

        for (Object[] result : results) {
            String nickName = (String) result[0];
            int score = (int) result[1];
            RankingTopDTO dto = new RankingTopDTO(nickName, score);
            dtos.add(dto);
        }
        return dtos;
    }

}