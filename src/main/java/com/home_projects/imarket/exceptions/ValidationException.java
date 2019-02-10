package com.home_projects.imarket.exceptions;

public class ValidationException extends RuntimeException {
    private static String DEFAULT_ERROR = "Validation error";

    public ValidationException() {
        super(DEFAULT_ERROR);
    }

    public ValidationException(String message) {
        super(DEFAULT_ERROR + " : " + message);
    }
}
