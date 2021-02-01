package com.github.lithualien.ibanvalidator.exceptions;

public class InvalidIbanException extends RuntimeException {

    public InvalidIbanException(String message) {
        super(message);
    }
}
