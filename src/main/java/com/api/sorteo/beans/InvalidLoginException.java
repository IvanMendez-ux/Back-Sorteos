package com.api.sorteo.beans;

public class InvalidLoginException extends RuntimeException {
    private final int status;

    public InvalidLoginException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
