package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "You cannot sell more shares than you own.")
public class InsufficientSharesException extends Exception {
    public InsufficientSharesException() {
        super("You cannot sell more shares than you own.");
    }
}
