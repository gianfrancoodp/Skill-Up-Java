package com.alkemy.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public ResponseEntity<Object> insufficientFundForTermFixedDepositException(InsufficientFundForFixedTermDepositException e) {
        ExceptionDetails exception = new ExceptionDetails(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> invalidFixedTermDepositTimeException(InvalidFixedTermDepositTimeException e) {
        ExceptionDetails exception = new ExceptionDetails(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> nonOwnAccountException(NonOwnAccountException e) {
        ExceptionDetails exception = new ExceptionDetails(
                e.getMessage(),
                e,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
