package io.renatofreire.todo_app_list.dto;

import io.renatofreire.todo_app_list.model.TaskStatus;

import java.util.UUID;

public record TaskDTO(String description, TaskStatus status, Long user) { }
