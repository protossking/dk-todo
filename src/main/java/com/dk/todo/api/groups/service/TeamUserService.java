package com.dk.todo.api.groups.service;

import com.dk.todo.domain.teams.TeamUserRepository;
import com.dk.todo.domain.teams.TeamUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamUserService {

    private final TeamUserRepository teamUserRepository;



    public Long addTeamUser(Long teamId, Long userId) {

        return teamUserRepository.save(new TeamUsers(teamId, userId))
                .getId();
    }
}
