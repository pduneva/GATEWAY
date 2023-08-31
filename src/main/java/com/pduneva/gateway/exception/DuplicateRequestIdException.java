package com.pduneva.gateway.exception;

public class DuplicateRequestIdException extends Exception {
    public DuplicateRequestIdException(String message) {
        super(message);
    }

    public DuplicateRequestIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateRequestIdException(Throwable cause) {
        super(cause);
    }
}