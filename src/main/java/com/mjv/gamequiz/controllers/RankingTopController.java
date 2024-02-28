package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.dtos.RankingTopDTO;
import com.mjv.gamequiz.services.RankingTopService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/raking-players")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class RankingTopController {

    private final RankingTopService rankingTopService;

    @GetMapping
    public ResponseEntity<List<RankingTopDTO>> findAllByRanking() {
        return ResponseEntity.ok().body(rankingTopService.findAllByRanking());
    }

}