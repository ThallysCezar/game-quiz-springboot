package com.mjv.gamequiz.exceptions.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistException extends ResponseStatusException {

    public UserAlreadyExistException(){
        super(HttpStatus.CONFLICT, "Já existe um usuário com esse email");
    }

    public UserAlreadyExistException(String message){
        super(HttpStatus.CONFLICT, message);
    }

}