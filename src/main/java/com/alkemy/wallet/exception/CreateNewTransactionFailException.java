package com.alkemy.wallet.exception;

public class CreateNewTransactionFailException extends RuntimeException {

    public CreateNewTransactionFailException(String message) {
        super(message);
    }

    public CreateNewTransactionFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
