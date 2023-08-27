package io.renatofreire.todo_app_list.controller;

import io.renatofreire.todo_app_list.dto.UserDTO;
import io.renatofreire.todo_app_list.model.User;
import io.renatofreire.todo_app_list.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping(path = "{userId}")
    public User getUserById(@PathVariable final Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public User createNewUser(@RequestBody final UserDTO newUser){
        return userService.createNewUser(newUser);
    }

    @PutMapping(path = "{userId}")
    public UserDTO updateUser(@PathVariable Long userId, @RequestBody final UserDTO user){
        return userService.updateUser(userId, user);
    }

    @DeleteMapping(path = "{userId}")
    public boolean deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

}
