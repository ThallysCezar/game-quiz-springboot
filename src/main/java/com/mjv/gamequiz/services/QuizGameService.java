package com.mjv.gamequiz.services;

import com.mjv.gamequiz.builders.PlayerMapper;
import com.mjv.gamequiz.domains.Player;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.domains.User;
import com.mjv.gamequiz.dtos.PlayerDTO;
import com.mjv.gamequiz.dtos.QuizGameDTO;
import com.mjv.gamequiz.exceptions.PlayerException;
import com.mjv.gamequiz.exceptions.QuestionException;
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
                .orElseThrow(() -> new QuestionException("Questão não encontrada com o ID fornecido: " + quizGameDTO.getQuestionId()));

        return Objects.equals(question.getCorrectQuestionAlternativeID(), quizGameDTO.getChosenAlternativeId());
    }

    public PlayerDTO updatePlayerScore(String nickName, int scoreIncrement) {
        try {
            Player existingPlayer = playerRepository.findByNickName(nickName)
                    .orElseThrow(() -> new PlayerException("Jogador não encontrado com ID: " + nickName));

            int currentScore = existingPlayer.getScore() != null ? existingPlayer.getScore() : 0;
            int newScore = currentScore + scoreIncrement;
            existingPlayer.setScore(newScore);

            return playerMapper.toDTO(playerRepository.saveAndFlush(existingPlayer));
        } catch (Exception ex) {
            throw new PlayerException("Erro ao atualizar o score do jogador.");
        }
    }

}