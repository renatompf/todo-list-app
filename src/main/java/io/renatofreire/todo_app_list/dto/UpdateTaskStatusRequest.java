package io.renatofreire.todo_app_list.dto;

import io.renatofreire.todo_app_list.model.TaskStatus;

public record UpdateTaskStatusRequest(TaskStatus newStatus) {
}
