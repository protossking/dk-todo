package com.dk.todo.controller;

import com.dk.todo.domain.dto.TaskAddRequestDTO;
import com.dk.todo.domain.response.ApiResponse;
import com.dk.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;


    @PostMapping("")
    public ResponseEntity<?> postTask(@RequestBody TaskAddRequestDTO taskAddRequestDTO) {
        return new ResponseEntity<>(taskService.addTask(taskAddRequestDTO), HttpStatus.OK);
    }

//    @GetMapping("/list")
//    public ApiResponse<TaskListResponseDTO> getTask() {
//        return new ResponseEntity<>()
//    }
}
