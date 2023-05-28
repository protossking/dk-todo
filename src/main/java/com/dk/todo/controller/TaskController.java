package com.dk.todo.controller;

import com.dk.todo.domain.dto.TaskAddRequestDTO;
import com.dk.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;


    @PostMapping("")
    public ResponseEntity<?> postTask(@RequestBody TaskAddRequestDTO taskAddRequestDTO) {


        return new ResponseEntity<>(taskService.addTask(taskAddRequestDTO), HttpStatus.OK);
    }
}
