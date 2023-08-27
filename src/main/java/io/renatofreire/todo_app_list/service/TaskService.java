package io.renatofreire.todo_app_list.service;

import io.renatofreire.todo_app_list.dto.UpdateTaskStatusRequest;
import io.renatofreire.todo_app_list.dto.NewTaskDTO;
import io.renatofreire.todo_app_list.dto.TaskDTO;
import io.renatofreire.todo_app_list.exception.InvalidBodyException;
import io.renatofreire.todo_app_list.exception.TaskInTodoAlreadyExists;
import io.renatofreire.todo_app_list.mapper.TaskMapper;
import io.renatofreire.todo_app_list.model.Task;
import io.renatofreire.todo_app_list.model.TaskStatus;
import io.renatofreire.todo_app_list.model.User;
import io.renatofreire.todo_app_list.repository.TaskRepository;
import io.renatofreire.todo_app_list.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskDTO> getAll(){
        return taskRepository.findAll().stream().map(TaskMapper::mapToDTO).collect(Collectors.toList());
    }

    public Task getById(final Long id){
        return taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public Task createNewTask(final NewTaskDTO taskDTO) {

        if( taskDTO.description() == null || taskDTO.user() == null){
            throw new InvalidBodyException("Invalid body to create task");
        }

        Optional<User> optionalUser = userRepository.findById(taskDTO.user());
        if(optionalUser.isEmpty()){
            throw new EntityNotFoundException("User with id:" + taskDTO.user()  +  " does not exist");
        }

        if(taskRepository.existsByDescriptionAndStatus(taskDTO.description(), TaskStatus.TODO)){
            throw new TaskInTodoAlreadyExists("A task in TODO with this description already exists");
        }

        Task newTask = new Task(taskDTO.description(), optionalUser.get());
        taskRepository.save(newTask);
        return newTask;
    }

    public Task updateTaskStatus(final Long id, final UpdateTaskStatusRequest newStatus) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));

        if(task.getStatus() == newStatus.newStatus()){
            return task;
        }

        task.setStatus(newStatus.newStatus());

        taskRepository.save(task);
        return task;
    }

    public Task deleteTask(final Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task not found"));

        taskRepository.delete(task);
        return task;
    }

    public List<TaskDTO> getAllByUser(final Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return taskRepository.findAllByUserId(userId).stream().map(TaskMapper::mapToDTO).collect(Collectors.toList());
    }

}
