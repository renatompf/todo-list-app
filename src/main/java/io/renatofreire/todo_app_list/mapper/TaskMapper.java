package io.renatofreire.todo_app_list.mapper;

import io.renatofreire.todo_app_list.dto.TaskDTO;
import io.renatofreire.todo_app_list.model.Task;

public class TaskMapper {

    public static TaskDTO mapToDTO(Task task){
        return new TaskDTO(task.getDescription(), task.getStatus(), task.getUser().getUserId());
    }
}
