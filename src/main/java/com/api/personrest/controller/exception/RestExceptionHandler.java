package com.api.personrest.controller.exception;

import com.api.personrest.dto.MessageDTO;
import com.api.personrest.exception.PersonException;
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

    @ResponseStatus
    @ExceptionHandler(value = {PersonException.class})
    private ResponseEntity<Object> personException(PersonException ex,
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
        }

        return new MessageDTO(message);
    }
}
