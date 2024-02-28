package com.mjv.gamequiz.exceptions.Theme;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ThemeNotFoundException extends ResponseStatusException {

    public ThemeNotFoundException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Theme não encontrado com esse id");
    }

    public ThemeNotFoundException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

}