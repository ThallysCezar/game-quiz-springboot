package com.mjv.gamequiz.exceptions.Question;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class QuestionException extends ResponseStatusException {

    public QuestionException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Erro geral da questão");
    }

    public QuestionException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}