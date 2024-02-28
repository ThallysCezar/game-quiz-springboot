package com.mjv.gamequiz.exceptions.QuestionChoices;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuestionChoicesException extends ResponseStatusException {

    public QuestionChoicesException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Erro geral da QuestionChoices");
    }

    public QuestionChoicesException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}