package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.domains.QuestionChoices;
import com.mjv.gamequiz.dtos.QuestionChoicesDTO;
import com.mjv.gamequiz.services.QuestionChoicesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question-choices")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class QuestionChoicesController {

    private final QuestionChoicesService questionChoicesService;

   @GetMapping("/{id}")
    public ResponseEntity<QuestionChoicesDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(questionChoicesService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<QuestionChoicesDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(questionChoicesService.findAll(pageable));
    }

    @GetMapping("/question-id/{id}")
    public ResponseEntity<List<QuestionChoicesDTO>> findQuestionAlternativesByQuestionId(@PathVariable Long id) {
        return ResponseEntity.ok().body(questionChoicesService.findByQuestionId(id));
    }

    @GetMapping("/count-by-theme")
    public List<Object[]> countAlternativesByTheme() {
        return questionChoicesService.countAlternativesByTheme();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody QuestionChoicesDTO alternativeDTO) {
        questionChoicesService.save(alternativeDTO);
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody List<QuestionChoices> alternativeList) {
        questionChoicesService.saveAll(alternativeList);
    }

}