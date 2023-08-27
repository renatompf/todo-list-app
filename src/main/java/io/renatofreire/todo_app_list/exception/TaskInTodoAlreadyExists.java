package io.renatofreire.todo_app_list.exception;

public class TaskInTodoAlreadyExists extends RuntimeException {
    public TaskInTodoAlreadyExists() {
    }

    public TaskInTodoAlreadyExists(String message) {
        super(message);
    }
}
