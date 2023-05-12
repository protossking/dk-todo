package com.dk.todo.service;

import com.dk.todo.dto.request.TaskAddRequestDTO;
import com.dk.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;


    public Integer addTask(TaskAddRequestDTO taskAddRequestDTO) {


//        taskRepository.save(taskAddRequestDTO.toEntity());

        return null;

    }
}
