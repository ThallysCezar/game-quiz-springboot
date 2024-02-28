package com.mjv.gamequiz.services;

import com.mjv.gamequiz.mappers.PlayerMapper;
import com.mjv.gamequiz.domains.Player;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.dtos.QuizGameDTO;
import com.mjv.gamequiz.exceptions.Player.PlayerException;
import com.mjv.gamequiz.exceptions.Player.PlayerNotFoundException;
import com.mjv.gamequiz.exceptions.Question.QuestionNotFoundException;
import com.mjv.gamequiz.repositories.PlayerRepository;
import com.mjv.gamequiz.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class QuizGameService {

    private final QuestionRepository questionRepository;
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public boolean checkAnswer(QuizGameDTO quizGameDTO) {
        Question question = questionRepository.findById(quizGameDTO.getQuestionId())
                .orElseThrow(() -> new QuestionNotFoundException("Questão não encontrada com o ID fornecido: " + quizGameDTO.getQuestionId()));

        return Objects.equals(question.getCorrectAlternativeID(), quizGameDTO.getChosenAlternativeId());
    }

    public void updatePlayerScore(String nickName, int scoreIncrement) {
        try {
            Player existingPlayer = playerRepository.findByNickName(nickName)
                    .orElseThrow(() -> new PlayerNotFoundException("Jogador não encontrado com ID: " + nickName));

            int currentScore = existingPlayer.getScore() != null ? existingPlayer.getScore() : 0;
            int newScore = currentScore + scoreIncrement;
            existingPlayer.setScore(newScore);

            playerMapper.toDTO(playerRepository.saveAndFlush(existingPlayer));
        } catch (Exception ex) {
            throw new PlayerException("Erro ao atualizar o score do jogador.");
        }
    }

}