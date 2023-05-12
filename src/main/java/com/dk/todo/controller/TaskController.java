package com.dk.todo.controller;

import com.dk.todo.domain.Member;
import com.dk.todo.dto.ResponseDTO;
import com.dk.todo.dto.request.TaskAddRequestDTO;
import com.dk.todo.service.TaskService;
import com.dk.todo.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "할일")
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
@RestController
public class TaskController {

    private final TaskService taskService;



    @PostMapping("/")
    public ResponseDTO<?> postTask(@RequestBody @Valid TaskAddRequestDTO taskAddRequestDTO) {

        return ResponseUtil.SUCCESS(taskService.addTask(taskAddRequestDTO));
    }

}
