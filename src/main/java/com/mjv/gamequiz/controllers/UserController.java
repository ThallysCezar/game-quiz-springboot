package com.mjv.gamequiz.controllers;

import com.mjv.gamequiz.dtos.UserDTO;
import com.mjv.gamequiz.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@Valid @PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{email}/{password}")
    public ResponseEntity<UserDTO> login(@Valid @PathVariable String email, @Valid @PathVariable String password, HttpSession httpSession)  throws NoSuchAlgorithmException {
       return ResponseEntity.ok().body(userService.findByEmailAndPassword(email, password));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody UserDTO dto) {
        userService.save(dto);
    }

}