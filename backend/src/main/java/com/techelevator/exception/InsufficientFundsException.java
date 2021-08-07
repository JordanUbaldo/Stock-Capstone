package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Insufficient Balance")
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("You do not have enough money available to make that trade.");
    }
}
