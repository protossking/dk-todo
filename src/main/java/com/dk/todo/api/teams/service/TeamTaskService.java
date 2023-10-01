package com.dk.todo.api.teams.service;

import com.dk.todo.domain.teams.TeamTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamTaskService {


    private final TeamTaskRepository teamTaskRepository;



    public void deleteTeamTask(Long teamId) {
        teamTaskRepository.deleteAllByTeamId(teamId);
    }


}
