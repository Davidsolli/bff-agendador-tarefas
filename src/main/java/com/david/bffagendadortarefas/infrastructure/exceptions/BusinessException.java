package com.david.bffagendadortarefas.infrastructure.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
