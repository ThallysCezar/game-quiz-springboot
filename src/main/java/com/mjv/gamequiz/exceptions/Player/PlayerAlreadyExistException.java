package com.mjv.gamequiz.exceptions.Player;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PlayerAlreadyExistException extends ResponseStatusException {

    public PlayerAlreadyExistException() {
        super(HttpStatus.CONFLICT, "O player com esse nickname já existe, tente novamente");
    }

    public PlayerAlreadyExistException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

}