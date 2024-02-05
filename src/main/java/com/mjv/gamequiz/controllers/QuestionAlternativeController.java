package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.domains.QuestionAlternative;
import com.mjv.gamequiz.dtos.QuestionAlternativeDTO;
import com.mjv.gamequiz.services.QuestionAlternativeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questionsAlternatives")
@AllArgsConstructor
public class QuestionAlternativeController {

    private final QuestionAlternativeService questionAlternativeService;

    @GetMapping
    public ResponseEntity<List<QuestionAlternativeDTO>> findAll() {
        return ResponseEntity.ok().body(questionAlternativeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionAlternativeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(questionAlternativeService.findById(id));
    }

    @GetMapping("/question-id/{id}")
    public ResponseEntity<List<QuestionAlternativeDTO>> findQuestionAlternativesByQuestionId(@PathVariable Long id) {
        return ResponseEntity.ok().body(questionAlternativeService.findByQuestionId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody QuestionAlternativeDTO questionAlternativeDTO) {
        questionAlternativeService.save(questionAlternativeDTO);
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody List<QuestionAlternative> questionAlternativeList) {
        questionAlternativeService.saveAll(questionAlternativeList);
    }

}