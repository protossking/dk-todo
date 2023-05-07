package com.dk.todo.controller;

import com.dk.todo.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "할일")
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
@RestController
public class TaskController {

    private final TaskService taskService;


}
