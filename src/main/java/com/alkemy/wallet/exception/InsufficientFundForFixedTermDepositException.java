package com.alkemy.wallet.exception;

public class InsufficientFundForFixedTermDepositException extends RuntimeException {

    public InsufficientFundForFixedTermDepositException(String message) {
        super(message);
    }

    public InsufficientFundForFixedTermDepositException(String message, Throwable cause) {
        super(message, cause);
    }
}