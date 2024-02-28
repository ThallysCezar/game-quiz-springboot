package com.mjv.gamequiz.exceptions.Theme;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ThemeException extends ResponseStatusException {

    public ThemeException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Erro geral Theme");
    }

    public ThemeException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

}