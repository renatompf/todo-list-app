package io.renatofreire.todo_app_list.exception;

public class InvalidBodyException extends RuntimeException {
    public InvalidBodyException() {
    }

    public InvalidBodyException(String message) {
        super(message);
    }
}
