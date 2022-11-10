package com.alkemy.wallet.exception;

public class InvalidFixedTermDepositTimeException extends RuntimeException {

    public InvalidFixedTermDepositTimeException(String message) {
        super(message);
    }

    public InvalidFixedTermDepositTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}