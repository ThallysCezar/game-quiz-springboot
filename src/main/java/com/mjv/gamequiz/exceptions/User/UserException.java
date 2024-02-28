package com.mjv.gamequiz.exceptions.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserException extends ResponseStatusException {

    public UserException(){
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Erro geral User");
    }

    public UserException(String message){
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}