package com.pduneva.gateway.exception;

public class CurrencyRetrieveException extends Exception {
    public CurrencyRetrieveException(String message) {
        super(message);
    }

    public CurrencyRetrieveException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyRetrieveException(Throwable cause) {
        super(cause);
    }

}
