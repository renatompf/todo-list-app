package io.renatofreire.todo_app_list.controller;

import io.renatofreire.todo_app_list.dto.NewTaskDTO;
import io.renatofreire.todo_app_list.dto.TaskDTO;
import io.renatofreire.todo_app_list.dto.UpdateTaskStatusRequest;
import io.renatofreire.todo_app_list.model.Task;
import io.renatofreire.todo_app_list.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDTO> getAll(){
        return taskService.getAll();
    }

    @GetMapping("/by-user/{userId}")
    public List<TaskDTO> getAllByUser(@PathVariable("userId") Long userId){
        return taskService.getAllByUser(userId);
    }

    @GetMapping("/{taskId}")
    public Task getById(@PathVariable("taskId") final Long taskId){
        return taskService.getById(taskId);
    }

    @PostMapping
    public Task createNewTask(@RequestBody final NewTaskDTO newTask){
        return taskService.createNewTask(newTask);
    }

    @PutMapping(path = "/{taskId}")
    public Task updateTaskStatus(@PathVariable("taskId") final Long taskId, @RequestBody final UpdateTaskStatusRequest updatedStatus){
        return taskService.updateTaskStatus(taskId, updatedStatus);
    }

    @DeleteMapping("/{taskId}")
    public Task deleteTask(@PathVariable("taskId") final Long taskId){
        return taskService.deleteTask(taskId);
    }

}
