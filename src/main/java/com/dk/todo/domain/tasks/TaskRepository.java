package com.dk.todo.domain.tasks;

import com.dk.todo.domain.tasks.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    List<Task> findByUsers_Id(Long userId);


    List<Task> findByUsers_IdAndIsBookmarkIsTrue (Long userId);

}
