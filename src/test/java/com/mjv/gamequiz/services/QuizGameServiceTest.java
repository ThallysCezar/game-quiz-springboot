package com.mjv.gamequiz.services;

import com.mjv.gamequiz.mappers.PlayerMapper;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.dtos.QuizGameDTO;
import com.mjv.gamequiz.exceptions.Player.PlayerException;
import com.mjv.gamequiz.repositories.PlayerRepository;
import com.mjv.gamequiz.repositories.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

@DisplayName("Service de QuizGame")
public class QuizGameServiceTest {

    private QuizGameService sut;
    private QuestionRepository repository;
    private PlayerRepository playerRepository;

    @BeforeEach
    void inicializarMocks() {
        this.repository = Mockito.mock(QuestionRepository.class);
        this.playerRepository = Mockito.mock(PlayerRepository.class);
        PlayerMapper mapper = Mockito.mock(PlayerMapper.class);
        this.sut = new QuizGameService(repository, playerRepository, mapper);
    }

    @Test
    @DisplayName("Deve verificar a resposta correta")
    void deveVerificarRespostaCorreta() {
        Question question = new Question();
        question.setId(1L);
        question.setCorrectAlternativeID(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(question));

        QuizGameDTO quizGameDTO = new QuizGameDTO();
        quizGameDTO.setQuestionId(1L);
        quizGameDTO.setChosenAlternativeId(1L);

        boolean result = sut.checkAnswer(quizGameDTO);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Deve verificar a resposta incorreta")
    void deveVerificarRespostaIncorreta() {
        Question question = new Question();
        question.setId(1L);
        question.setCorrectAlternativeID(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(question));

        QuizGameDTO quizGameDTO = new QuizGameDTO();
        quizGameDTO.setQuestionId(1L);
        quizGameDTO.setChosenAlternativeId(2L);

        boolean result = sut.checkAnswer(quizGameDTO);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Deve lançar PlayerException ao tentar atualizar o score de um jogador inexistente")
    void deveLancarPlayerExceptionAtualizarScoreJogadorInexistente() {
        Mockito.when(playerRepository.findByNickName("player2")).thenReturn(Optional.empty());

        Assertions.assertThrows(PlayerException.class, () -> sut.updatePlayerScore("player2", 50));
    }

}