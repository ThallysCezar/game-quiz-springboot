package com.mjv.gamequiz.exceptions.QuestionChoices;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuestionChoicesNotFoundException extends ResponseStatusException {

    public QuestionChoicesNotFoundException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "QuestionChoices não encontrada com id");
    }

    public QuestionChoicesNotFoundException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

}