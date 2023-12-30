package com.xteicel.todoapi.exceptions;

public class TodoItemException extends RuntimeException{
    public TodoItemException() {
        super();
    }

    public TodoItemException(String message) {
        super(message);
    }

    public TodoItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public TodoItemException(Throwable cause) {
        super(cause);
    }

    protected TodoItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
