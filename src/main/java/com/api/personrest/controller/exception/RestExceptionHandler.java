package com.api.personrest.controller.exception;

import com.api.personrest.dto.MessageDTO;
import com.api.personrest.exception.PersonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static java.util.stream.Collectors.joining;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseEntityExceptionHandler.class);

    @ResponseStatus
    @ExceptionHandler(value = {PersonException.class})
    protected ResponseEntity<Object> personException(PersonException ex,
                                                     WebRequest request) {

        return handleExceptionInternal(ex,
                mountMessage(ex),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    private MessageDTO mountMessage(RuntimeException ex) {
        String message = ex.getMessage();

        if (ex.getCause() instanceof ConstraintViolationException) {
            message = message + ((ConstraintViolationException) ex.getCause())
                    .getConstraintViolations()
                    .stream()
                    .map(data -> "[".concat(data.getPropertyPath().toString()).concat(" : ")
                            .concat(data.getMessageTemplate()).concat("]")).collect(joining(", "));
        } else if (ex.getCause() instanceof IllegalArgumentException) {
            logger.error(ex.getCause().getMessage());
        }

        return new MessageDTO(message);
    }
}
