package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.dtos.QuizGameDTO;
import com.mjv.gamequiz.services.QuestionService;
import com.mjv.gamequiz.services.QuizGameService;
import com.mjv.gamequiz.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game-quiz")
@AllArgsConstructor
public class QuizController {

    private final QuestionService questionService;
    private final UserService userService;
    private final QuizGameService quizGameService;

    @GetMapping("/loginByUser/{login}/theme/{theme}")
    public ResponseEntity<List<QuestionDTO>> findQuestionByUserAndTheme(@PathVariable String login, @PathVariable String theme) {
        if (!userService.userExists(login)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<QuestionDTO> questions = questionService.getQuestionsByTheme(theme);
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/answer")
    public ResponseEntity<?> submitAnswer(@RequestBody QuizGameDTO quizGameDTO) {
        boolean isCorrect = quizGameService.checkAnswer(quizGameDTO);
        final var nickName = quizGameDTO.getNickName();
        if (isCorrect) {
            quizGameService.updatePlayerScore(nickName, 100);
            return ResponseEntity.ok(String.format("Resposta Correta! Boa %s", nickName));
        } else {
            return ResponseEntity.ok(String.format("Resposta incorreta, tente novamente, %s!", nickName));
        }
    }

}