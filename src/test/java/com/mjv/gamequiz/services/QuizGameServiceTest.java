package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.PlayerMapper;
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
    private PlayerMapper mapper;

    @BeforeEach
    void inicializarMocks() {
        this.repository = Mockito.mock(QuestionRepository.class);
        this.playerRepository = Mockito.mock(PlayerRepository.class);
        this.mapper = Mockito.mock(PlayerMapper.class);
        this.sut = new QuizGameService(repository, playerRepository, mapper);
    }

    @Test
    @DisplayName("Deve verificar a resposta correta")
    void deveVerificarRespostaCorreta() {
        // Criar uma questão com uma alternativa correta
        Question question = new Question();
        question.setId(1L);
        question.setCorrectAlternativeID(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(question));

        // Criar um DTO com a resposta correta
        QuizGameDTO quizGameDTO = new QuizGameDTO();
        quizGameDTO.setQuestionId(1L);
        quizGameDTO.setChosenAlternativeId(1L);

        // Verificar se a resposta está correta
        boolean result = sut.checkAnswer(quizGameDTO);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Deve verificar a resposta incorreta")
    void deveVerificarRespostaIncorreta() {
        // Criar uma questão com uma alternativa correta
        Question question = new Question();
        question.setId(1L);
        question.setCorrectAlternativeID(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(question));

        // Criar um DTO com a resposta incorreta
        QuizGameDTO quizGameDTO = new QuizGameDTO();
        quizGameDTO.setQuestionId(1L);
        quizGameDTO.setChosenAlternativeId(2L);

        // Verificar se a resposta está incorreta
        boolean result = sut.checkAnswer(quizGameDTO);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Deve lançar PlayerException ao tentar atualizar o score de um jogador inexistente")
    void deveLancarPlayerExceptionAtualizarScoreJogadorInexistente() {
        // Mock do jogador inexistente
        Mockito.when(playerRepository.findByNickName("player2")).thenReturn(Optional.empty());

        // Verificar se a exceção é lançada ao tentar atualizar o score de um jogador inexistente
        Assertions.assertThrows(PlayerException.class, () -> sut.updatePlayerScore("player2", 50));
    }
}
