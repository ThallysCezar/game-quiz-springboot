package com.mjv.gamequiz.exceptions.Player;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PlayerException extends ResponseStatusException {

    public PlayerException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Erro geral do Player");
    }

    public PlayerException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}