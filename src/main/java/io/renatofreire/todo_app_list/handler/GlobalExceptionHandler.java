package io.renatofreire.todo_app_list.handler;

import io.renatofreire.todo_app_list.exception.EmailAlreadyTakenException;
import io.renatofreire.todo_app_list.exception.EmailAndNameComboAlreadyTakenException;
import io.renatofreire.todo_app_list.exception.InvalidBodyException;
import io.renatofreire.todo_app_list.exception.TaskInTodoAlreadyExists;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = EmailAlreadyTakenException.class)
    ResponseEntity<String> EmailAlreadyTakenExceptionHandler(EmailAlreadyTakenException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT );
    }

    @ExceptionHandler(value = EmailAndNameComboAlreadyTakenException.class)
    public ResponseEntity<String> EmailAndNameComboAlreadyTakenExceptionHandler(EmailAndNameComboAlreadyTakenException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = InvalidBodyException.class)
    public ResponseEntity<String> InvalidBodyExceptionHandler(InvalidBodyException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TaskInTodoAlreadyExists.class)
    public ResponseEntity<String> TaskInTodoAlreadyExistsHandler(TaskInTodoAlreadyExists exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<String> EntityNotFoundExceptionHandler(EntityNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

}
