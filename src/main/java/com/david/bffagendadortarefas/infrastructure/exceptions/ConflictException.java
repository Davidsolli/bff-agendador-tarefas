package com.david.bffagendadortarefas.infrastructure.exceptions;

public class ConflictException extends RuntimeException {

    public ConflictException (String message) {
        super(message);
    }

    public ConflictException(String message, Throwable throwable) {
        super(message);
    }
}
