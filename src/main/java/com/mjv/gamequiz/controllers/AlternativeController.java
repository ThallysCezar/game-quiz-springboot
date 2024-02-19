package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.domains.Alternative;
import com.mjv.gamequiz.dtos.AlternativeDTO;
import com.mjv.gamequiz.services.AlternativeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questionsAlternatives")
@AllArgsConstructor
public class AlternativeController {

    private final AlternativeService alternativeService;

   @GetMapping("/{id}")
    public ResponseEntity<AlternativeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(alternativeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AlternativeDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(alternativeService.findAll(pageable));
    }

    @GetMapping("/question-id/{id}")
    public ResponseEntity<List<AlternativeDTO>> findQuestionAlternativesByQuestionId(@PathVariable Long id) {
        return ResponseEntity.ok().body(alternativeService.findByQuestionId(id));
    }

    @GetMapping("/count-by-theme")
    public List<Object[]> countAlternativesByTheme() {
        return alternativeService.countAlternativesByTheme();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody AlternativeDTO alternativeDTO) {
        alternativeService.save(alternativeDTO);
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody List<Alternative> alternativeList) {
        alternativeService.saveAll(alternativeList);
    }

}