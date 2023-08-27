package io.renatofreire.todo_app_list.model;


import io.renatofreire.todo_app_list.dto.UserDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "account")
@Entity
public class User {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String name;
    private String email;


    public User() {
    }

    public User(UserDTO user){
        this.name = user.name();
        this.email = user.email();
    }

    public User(Long userId, String name, String email) {
        this.id = userId;
        this.name = name;
        this.email = email;
    }

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.id = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(UserDTO user){
        return user.email().equals(this.getEmail()) && user.name().equals(this.getName()) ;
    }

}
