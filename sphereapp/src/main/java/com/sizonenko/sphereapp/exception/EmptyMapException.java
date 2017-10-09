package com.sizonenko.sphereapp.exception;

public class EmptyMapException extends Exception {

    public EmptyMapException() {
    }

    public EmptyMapException(String message) {
        super(message);
    }

    public EmptyMapException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyMapException(Throwable cause) {
        super(cause);
    }
}
