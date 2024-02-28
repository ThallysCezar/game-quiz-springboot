package com.mjv.gamequiz.exceptions.Question;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuestionNotFoundException extends ResponseStatusException {


    public QuestionNotFoundException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Questão não encontrada com esse id");
    }

    public QuestionNotFoundException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

}