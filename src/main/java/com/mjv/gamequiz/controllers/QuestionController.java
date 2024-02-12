package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.services.QuestionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> findById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok().body(questionService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> findAllQuestions(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(questionService.findAllQuestionsWithAlternatives());
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
