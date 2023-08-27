package io.renatofreire.todo_app_list.mapper;

import io.renatofreire.todo_app_list.dto.UserDTO;
import io.renatofreire.todo_app_list.model.User;

public class UserMapper {

    public static UserDTO mapToDTO(User user){
        return new UserDTO(user.getName(), user.getEmail());
    }
}
