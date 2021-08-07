package com.techelevator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Insufficient shares")
public class NonExistentStockException extends Exception {
    public NonExistentStockException() {
        super("That stock does not exist.");
    }
}
