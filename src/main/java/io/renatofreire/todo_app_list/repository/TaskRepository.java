package io.renatofreire.todo_app_list.repository;

import io.renatofreire.todo_app_list.model.Task;
import io.renatofreire.todo_app_list.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByDescriptionAndStatus(String description, TaskStatus status);

    @Query(value = "SELECT t FROM Task t WHERE t.assignee.id = :userId")
    List<Task> findAllByUserId(@Param("userId")Long userId);

}
