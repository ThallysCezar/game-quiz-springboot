package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.domains.Player;
import com.mjv.gamequiz.dtos.PlayerDTO;
import com.mjv.gamequiz.services.PlayerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> findPlayerById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok().body(playerService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> findAll() {
        return ResponseEntity.ok().body(playerService.findAll());
    }

    @PutMapping("/update")
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO dto){
        playerService.updatePlayer(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody PlayerDTO dto) {
        playerService.save(dto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlayer(@Valid @PathVariable Long id){
        playerService.deletePlayer(id);
    }

}
