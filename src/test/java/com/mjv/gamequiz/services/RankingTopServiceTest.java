package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.QuestionMapper;
import com.mjv.gamequiz.dtos.RankingTopDTO;
import com.mjv.gamequiz.repositories.PlayerRepository;
import com.mjv.gamequiz.repositories.QuestionRepository;
import com.mjv.gamequiz.repositories.ThemeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@DisplayName("Service de RankingTop")
public class RankingTopServiceTest {

    private PlayerRepository repository;
    private RankingTopService sut;

    @BeforeEach
    void inicializarMocks() {
        this.repository = Mockito.mock(PlayerRepository.class);
        this.sut = new RankingTopService(repository);
    }

    @Test
    @DisplayName("Deve retornar a lista de jogadores do ranking")
    void deveRetornarListaJogadoresRanking() {
        Object[] result1 = {"Player1", 100};
        Object[] result2 = {"Player2", 90};
        List<Object[]> expectedResult = List.of(result1, result2);

        Mockito.when(repository.findAllByRanking()).thenReturn(expectedResult);

        List<RankingTopDTO> result = sut.findAllByRanking();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Player1", result.get(0).getNickName());
        Assertions.assertEquals(100, result.get(0).getScore());
        Assertions.assertEquals("Player2", result.get(1).getNickName());
        Assertions.assertEquals(90, result.get(1).getScore());
    }
}
