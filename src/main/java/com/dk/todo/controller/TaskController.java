package com.dk.todo.controller;

import com.dk.todo.config.oauth.dto.SessionUser;
import com.dk.todo.domain.dto.TaskAddRequestDTO;
import com.dk.todo.domain.dto.TaskDTO;
import com.dk.todo.domain.response.ApiResponse;
import com.dk.todo.service.TaskService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;


    @PostMapping("")
    public ResponseEntity<?> postTask(@RequestBody @Valid TaskAddRequestDTO taskAddRequestDTO) {
        return new ResponseEntity<>(taskService.addTask(taskAddRequestDTO), HttpStatus.OK);
    }

    @GetMapping("")
    public ApiResponse<List<TaskDTO.TaskResponse>> getTask(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {

        return ApiResponse.createSuccess(taskService.findTask(userDetails));

    }
}
