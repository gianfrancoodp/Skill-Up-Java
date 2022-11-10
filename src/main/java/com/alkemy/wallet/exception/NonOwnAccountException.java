package com.alkemy.wallet.exception;

public class NonOwnAccountException extends RuntimeException{

    public NonOwnAccountException(String message) {
        super(message);
    }

    public NonOwnAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
