package com.dk.todo.service;

import com.dk.todo.domain.Task;
import com.dk.todo.domain.Users;
import com.dk.todo.domain.dto.TaskDTO;
import com.dk.todo.domain.enums.TaskStatus;
import com.dk.todo.repository.TaskRepository;
import com.dk.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;


    @Transactional
    public Long addTask(TaskDTO.TaskRequest taskAddRequestDTO, Long userId) {


        Users findUser = userRepository.findById(userId).get();



        return taskRepository.save(taskAddRequestDTO.toEntity(findUser)).getId();
    }

    public Map<TaskStatus, List<TaskDTO.TaskResponse>> findTask(Long userId) {

        Map<TaskStatus, List<TaskDTO.TaskResponse>> result = taskRepository.findByUsers_Id(userId).stream()
                .map(t -> new TaskDTO.TaskResponse(t.getId(), t.getTitle(), t.getTitleEmoji(), t.getDescription(), t.getStatus(), t.getStartedDt(), t.getEndedDt(), t.getIsBookmark(), t.getBackgroundColor()))
                .collect(Collectors.toList()).stream().collect(Collectors.groupingBy(TaskDTO.TaskResponse::getTaskStatus));

        if(!result.containsKey(TaskStatus.TODO)) {
            result.put(TaskStatus.TODO, new ArrayList<>());
        }

        if(!result.containsKey(TaskStatus.DOING)) {
            result.put(TaskStatus.DOING, new ArrayList<>());
        }

        if(!result.containsKey(TaskStatus.DONE)) {
            result.put(TaskStatus.DONE, new ArrayList<>());
        }



        return result;

    }

    @Transactional
    public TaskDTO.TaskUpdateResponse updateTask(Long taskId, TaskDTO.TaskUpdateRequest taskUpdateRequest) {

        /*
        일자 기준으로 TODO 상태로 돌리는거 예외처리해야함
         */

        Task findTask = taskRepository.findById(taskId).get();
        findTask.updateTaskStatus(taskUpdateRequest.getTaskStatus());

        return new TaskDTO.TaskUpdateResponse(findTask.getId(), findTask.getStatus());
    }

    @Transactional
    public TaskDTO.TaskDeleteResponse deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);

        return new TaskDTO.TaskDeleteResponse(taskId);
    }


    public Map<TaskStatus, List<TaskDTO.TaskResponse>> findBookmarkedTask(Long userId) {

        return taskRepository.findByUsers_IdAndIsBookmarkIsTrue(userId).stream()
                .map(t -> new TaskDTO.TaskResponse(t.getId(), t.getTitle(), t.getTitleEmoji(), t.getDescription(), t.getStatus(), t.getStartedDt(), t.getEndedDt(), t.getIsBookmark(), t.getBackgroundColor()))
                .collect(Collectors.toList()).stream().collect(Collectors.groupingBy(TaskDTO.TaskResponse::getTaskStatus));

    }

    @Transactional
    public TaskDTO.TaskBookmarkUpdateResponse updateBookmark (Long userId, Long taskId) {

        Task findTask = taskRepository.findById(taskId).get();

        findTask.updateBookmark();


        return new TaskDTO.TaskBookmarkUpdateResponse(findTask.getId(), findTask.getIsBookmark());
    }

}
