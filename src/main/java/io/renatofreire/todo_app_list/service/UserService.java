package io.renatofreire.todo_app_list.service;

import io.renatofreire.todo_app_list.dto.UserDTO;
import io.renatofreire.todo_app_list.exception.EmailAlreadyTakenException;
import io.renatofreire.todo_app_list.exception.EmailAndNameComboAlreadyTakenException;
import io.renatofreire.todo_app_list.exception.InvalidBodyException;
import io.renatofreire.todo_app_list.mapper.UserMapper;
import io.renatofreire.todo_app_list.model.User;
import io.renatofreire.todo_app_list.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(final Long userId) throws EntityNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public User createNewUser(final UserDTO user) {
        if(user.email() == null || user.name() == null){
            throw new InvalidBodyException("Invalid body");
        }

        if(userRepository.findByEmail(user.email()).isPresent()){
            throw new EmailAlreadyTakenException(String.format("The email {1} is already taken", user.email()));
        }

        User newUser = new User(user);
        userRepository.save(newUser);
        return newUser;
    }

    public UserDTO updateUser(final Long userId, final UserDTO updatedUser) throws EmailAlreadyTakenException, InvalidBodyException, EmailAndNameComboAlreadyTakenException {
        User user;

        if(userId == null){
            throw new InvalidBodyException("Invalid body");
        }

        if(updatedUser.email() == null || updatedUser.name() == null){
            throw new InvalidBodyException("Invalid body");
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }else{
            throw new EntityNotFoundException("User with id:" + userId  +  " does not exist");
        }

        if(user.equals(updatedUser)){
            return updatedUser;
        }

        if(userRepository.existsByEmail(updatedUser.email())){
            throw new EmailAlreadyTakenException(
                    String.format("The email {1} is already taken. It was not possible to update your account for this email", updatedUser.email())
            );
        }

        if(userRepository.existsByEmailAndName(updatedUser.email(), updatedUser.name())){
            throw new EmailAndNameComboAlreadyTakenException(
                    "The combo [name, email] is already taken. It was not possible to update your account for this email"
            );
        }

        user.setEmail(updatedUser.email());
        user.setName(updatedUser.name());

        userRepository.save(user);

        return updatedUser;
    }

    public boolean deleteUser(final Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return true;
        }else{
            throw new EntityNotFoundException("User with id:" + userId +  " does not exist");
        }
    }

}
