package com.dk.todo.service;

import com.dk.todo.config.oauth.dto.SessionUser;
import com.dk.todo.domain.Users;
import com.dk.todo.domain.dto.TaskAddRequestDTO;
import com.dk.todo.repository.TaskRepository;
import com.dk.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

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

}
