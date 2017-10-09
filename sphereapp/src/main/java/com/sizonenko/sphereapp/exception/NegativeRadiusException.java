package com.sizonenko.sphereapp.exception;

public class NegativeRadiusException extends Exception {

    public NegativeRadiusException() {
    }

    public NegativeRadiusException(String message) {
        super(message);
    }

    public NegativeRadiusException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeRadiusException(Throwable cause) {
        super(cause);
    }
}
