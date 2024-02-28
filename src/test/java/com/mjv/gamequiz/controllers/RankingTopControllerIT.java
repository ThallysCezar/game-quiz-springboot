package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.dtos.RankingTopDTO;
import com.mjv.gamequiz.factories.RankingTopFactory;
import com.mjv.gamequiz.services.RankingTopService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration Teste RankingTopController")
public class RankingTopControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RankingTopService rankingTopService;

    @Test
    @DisplayName("Deve retornar todos os usuários")
    void testFindAllByRanking() throws Exception {
        List<RankingTopDTO> rankingTopDTOList = Arrays.asList(RankingTopFactory.createValidRankingTopDTO(), RankingTopFactory.createValidRankingTopDTO());

        when(rankingTopService.findAllByRanking()).thenReturn(rankingTopDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/raking-players")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nickName").value(rankingTopDTOList.get(0).getNickName()))
                .andExpect(jsonPath("$[0].score").value(rankingTopDTOList.get(0).getScore()))
                .andExpect(jsonPath("$[1].nickName").value(rankingTopDTOList.get(1).getNickName()))
                .andExpect(jsonPath("$[1].score").value(rankingTopDTOList.get(1).getScore()));
    }

}