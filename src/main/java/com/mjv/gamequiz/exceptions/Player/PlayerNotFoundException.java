package com.mjv.gamequiz.exceptions.Player;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PlayerNotFoundException extends ResponseStatusException {

    public PlayerNotFoundException(){
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Player não encontrado com esse id");
    }

    public PlayerNotFoundException(String message){
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

}