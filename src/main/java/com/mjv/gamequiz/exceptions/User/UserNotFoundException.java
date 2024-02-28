package com.mjv.gamequiz.exceptions.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    public UserNotFoundException(){
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Usuario não encontrado com esse id");
    }

    public UserNotFoundException(String message){
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

}