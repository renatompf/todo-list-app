package io.renatofreire.todo_app_list.repository;

import io.renatofreire.todo_app_list.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmailAndName(String email, String name);

    boolean existsByEmail(String email);
}
