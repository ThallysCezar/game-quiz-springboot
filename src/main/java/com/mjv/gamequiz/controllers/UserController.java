package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.dtos.UserDTO;
import com.mjv.gamequiz.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<UserDTO> findByEmailPassword(@PathVariable String email, @PathVariable String password) {
        return ResponseEntity.ok().body(userService.findByEmailAndPassword(email, password));
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO dto) {
        return ResponseEntity.ok().body(userService.save(dto));
    }

}