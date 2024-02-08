package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.services.QuestionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> findById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok().body(questionService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> findAllQuestions() {
        return ResponseEntity.ok().body(questionService.findAll());
    }

    @GetMapping("/theme/{themeName}")
    public List<QuestionDTO> getQuestionsByThemeName(@PathVariable String themeName) {
        return questionService.getQuestionsByTheme(themeName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody QuestionDTO questionDTO) {
        questionService.save(questionDTO);
    }

}
