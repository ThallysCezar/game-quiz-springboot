package com.mjv.gamequiz.exceptions;

import com.mjv.gamequiz.exceptions.Player.PlayerAlreadyExistException;
import com.mjv.gamequiz.exceptions.Player.PlayerException;
import com.mjv.gamequiz.exceptions.Player.PlayerNotFoundException;
import com.mjv.gamequiz.exceptions.Question.QuestionException;
import com.mjv.gamequiz.exceptions.Question.QuestionNotFoundException;
import com.mjv.gamequiz.exceptions.QuestionChoices.QuestionChoicesException;
import com.mjv.gamequiz.exceptions.QuestionChoices.QuestionChoicesNotFoundException;
import com.mjv.gamequiz.exceptions.Theme.ThemeNotFoundException;
import com.mjv.gamequiz.exceptions.User.UserAlreadyExistException;
import com.mjv.gamequiz.exceptions.User.UserException;
import com.mjv.gamequiz.exceptions.User.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger LOGGER = LogManager.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler({UserNotFoundException.class,
            PlayerNotFoundException.class,
            QuestionNotFoundException.class,
            QuestionChoicesNotFoundException.class,
            ThemeNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleNotFoundException(Exception ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({UserAlreadyExistException.class, PlayerAlreadyExistException.class})
    public ResponseEntity<ErrorMessage> handleAlreadyExistException(Exception ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserException.class,
            PlayerException.class,
            QuestionException.class,
            QuestionChoicesException.class})
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessage> handleException(Exception ex, HttpServletRequest request, HttpStatus status) {
        String messageError = "Api Error: ";
        LOGGER.error(messageError, ex);
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, status, ex.getMessage()));
    }

}