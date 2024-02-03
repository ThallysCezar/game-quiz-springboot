package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.domains.QuestionAlternative;
import com.mjv.gamequiz.dtos.QuestionAlternativeDTO;
import com.mjv.gamequiz.dtos.ValidateAlternativeDTO;
import com.mjv.gamequiz.services.QuestionAlternativeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questionsAlternatives")
@AllArgsConstructor
public class QuestionAlternativeController {

    private final QuestionAlternativeService questionAlternativeService;

    @GetMapping
    public ResponseEntity<List<QuestionAlternativeDTO>> findAll(){
        return ResponseEntity.ok().body(questionAlternativeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionAlternativeDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(questionAlternativeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<QuestionAlternativeDTO> save(@RequestBody QuestionAlternative questionAlternative){
        return ResponseEntity.ok().body(questionAlternativeService.save(questionAlternative));
    }

    @GetMapping("/question-id/{id}")
    public ResponseEntity<List<QuestionAlternativeDTO>> findQuestionAlternativesByQuestionId(@PathVariable Long id){
        return ResponseEntity.ok().body(questionAlternativeService.findByQuestionId(id));
    }

    @PostMapping("/list")
    public ResponseEntity<List<QuestionAlternativeDTO>> save(@RequestBody List<QuestionAlternative> questionAlternativeList){
        return ResponseEntity.ok().body(questionAlternativeService.saveAll(questionAlternativeList));
    }

    @PostMapping("/validate-alternative")
    public ResponseEntity<QuestionAlternativeDTO> validateAlternative(ValidateAlternativeDTO validateAlternativeDTO){
        return ResponseEntity.ok().body(questionAlternativeService
                .findByQuestionIdAndItsCorrect(validateAlternativeDTO.getId(), validateAlternativeDTO.getAlternativeStatus()));
    }

}
