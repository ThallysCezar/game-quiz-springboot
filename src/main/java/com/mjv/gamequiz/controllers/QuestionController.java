package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.dtos.QuestionDTO;
import com.mjv.gamequiz.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{id}")
    public QuestionDTO findById(@PathVariable Long id) {
        return questionService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> findAllQuestions() {
        return ResponseEntity.ok().body(questionService.findAll());
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> save(@RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.ok().body(questionService.save(questionDTO));
    }

}
