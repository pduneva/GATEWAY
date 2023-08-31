package com.pduneva.gateway.controller;

import com.pduneva.gateway.exception.DuplicateRequestIdException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value
            = { IllegalArgumentException.class, DuplicateRequestIdException.class })
    protected ResponseEntity handleDuplicateRequestId(Exception ex) {
        return ResponseEntity.badRequest().build();
    }
}
