package com.dk.todo.service;

import com.dk.todo.config.oauth.dto.SessionUser;
import com.dk.todo.domain.Task;
import com.dk.todo.domain.Users;
import com.dk.todo.domain.dto.TaskAddRequestDTO;
import com.dk.todo.domain.dto.TaskDTO;
import com.dk.todo.repository.TaskRepository;
import com.dk.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;


    @Transactional
    public Long addTask(TaskAddRequestDTO taskAddRequestDTO) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        Users users = userRepository.findByEmail(email).get();

        return taskRepository.save(taskAddRequestDTO.toEntity(users)).getId();
    }

    public List<TaskDTO.TaskResponse> findTask(UserDetails userDetails) {

        Long userId = userRepository.findByEmail(userDetails.getUsername()).get().getId();


        return taskRepository.findByUsers_Id(userId).stream()
                .map(t -> new TaskDTO.TaskResponse(t.getId(), t.getTitle(), t.getTitleEmoji(), t.getDescription(), t.getStatus(), t.getStartedDt(), t.getEndedDt()))
                .collect(Collectors.toList());



    }

}
