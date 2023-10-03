package com.dk.todo.api.teams.service;

import com.dk.todo.api.teams.service.dto.TeamDTO;
import com.dk.todo.domain.teams.TeamUserRepository;
import com.dk.todo.domain.teams.TeamUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamUserService {

    private final TeamUserRepository teamUserRepository;


    public Long addTeamUser(Long teamId, Long userId) {

        return teamUserRepository.save(new TeamUsers(teamId, userId))
                .getId();
    }

    public void deleteTeamUserByTeamId(Long teamId) {

        teamUserRepository.deleteAllByTeamId(teamId);
    }

    public void deleteTeamUserByUserId(Long userId) {
        teamUserRepository.deleteByUserId(userId);
    }

    public List<TeamDTO.TeamUserListResponse> findUserByTeamId(Long teamId) {

        return teamUserRepository.findUserByTeamId(teamId).stream()
                .map(tu -> new TeamDTO.TeamUserListResponse(tu.getId(), tu.getName(), tu.getEmail()))
                .collect(Collectors.toList());

    }


}
