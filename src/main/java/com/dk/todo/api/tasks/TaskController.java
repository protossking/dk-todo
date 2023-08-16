package com.dk.todo.api.tasks;

import com.dk.todo.config.oauth.dto.SessionUser;
import com.dk.todo.api.tasks.request.TaskDTO;
import com.dk.todo.domain.tasks.TaskStatus;
import com.dk.todo.response.ApiResponse;
import com.dk.todo.api.tasks.service.TaskService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;


    @PostMapping("")
    public ResponseEntity<?> postTask(@RequestBody @Valid TaskDTO.TaskRequest taskAddRequestDTO, @Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {
        return new ResponseEntity<>(taskService.addTask(taskAddRequestDTO, sessionUser.getId()), HttpStatus.OK);
    }

    @GetMapping("")
    public ApiResponse<Map<TaskStatus, List<TaskDTO.TaskResponse>>> findTask(@Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {

        return ApiResponse.createSuccess(taskService.findTask(sessionUser.getId()));
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<TaskDTO.TaskStatusUpdateResponse> updateTaskStatus(@PathVariable(value = "id") Long taskId, @RequestBody TaskDTO.TaskStatusUpdateRequest taskStatusUpdateRequest) {
        return ApiResponse.createSuccess(taskService.updateTaskStatus(taskId, taskStatusUpdateRequest));
    }

    @PatchMapping("/{id}/update")
    public ApiResponse<TaskDTO.TaskResponse> updateTask(@PathVariable(value = "id") Long taskId, @RequestBody TaskDTO.TaskUpdateRequest taskUpdateRequest) {
        return ApiResponse.createSuccess(taskService.updateTask(taskId, taskUpdateRequest));
    }


    @DeleteMapping("/{id}")
    public ApiResponse<TaskDTO.TaskDeleteResponse> deleteTask(@PathVariable(value = "id") Long taskId) {
        return ApiResponse.createSuccess(taskService.deleteTask(taskId));
    }

    @GetMapping("/bookmark")
    public ApiResponse<Map<TaskStatus, List<TaskDTO.TaskResponse>>> findBookmarkedTask(@Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {

        return ApiResponse.createSuccess(taskService.findBookmarkedTask(sessionUser.getId()));
    }

    @PatchMapping("/bookmark")
    public ApiResponse<TaskDTO.TaskBookmarkUpdateResponse> updateBookmark(@RequestBody Long taskId, @Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {
        return ApiResponse.createSuccess(taskService.updateBookmark(sessionUser.getId(), taskId));
    }
}
