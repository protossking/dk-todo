package com.dk.todo.service;

import com.dk.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
}
