package com.dk.todo.api.teams.service;

import com.dk.todo.api.teams.request.TeamRequestDTO;
import com.dk.todo.api.teams.service.dto.TeamDTO;
import com.dk.todo.api.invitation.service.InvitationService;
import com.dk.todo.domain.exception.teams.CannotDeleteTeamException;
import com.dk.todo.domain.exception.teams.NotFoundTeamExceptionId;
import com.dk.todo.domain.teams.TeamRepository;
import com.dk.todo.domain.teams.Teams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final InvitationService invitationService;
    private final TeamUserService teamUserService;

    private final TeamTaskService teamTaskService;
    private final TeamRepository teamRepository;


    @Transactional
    public TeamDTO.TeamAddResponse addTeam(TeamRequestDTO.TeamAddRequest teamAddRequest, Long userId) {

        Teams savedTeam = teamRepository.saveAndFlush(teamAddRequest.toEntity(userId));

        return TeamDTO.TeamAddResponse.builder()
                .teamId(savedTeam.getId())
                .teamName(savedTeam.getTeamName())
                .build();

    }

    public TeamDTO.TeamInviteResponse inviteTeam(TeamRequestDTO.TeamInviteRequest teamInviteRequest, Long userId) {

        invitationService.sendInvitation(userId, teamInviteRequest.getEmails(), teamInviteRequest.getTeamId());

        return null;

    }

    @Transactional
    public TeamDTO.TeamJoinResponse joinTeam(TeamRequestDTO.TeamJoinRequest teamJoinRequest, Long userId) {

        Long teamId = invitationService.findTeamId(teamJoinRequest.getInviteCode());

        teamUserService.addTeamUser(teamId, userId);


        return TeamDTO.TeamJoinResponse.builder()
                .teamId(teamId)
                .userId(userId)
                .build();

    }


    @Transactional
    public Boolean deleteTeam(Long teamId, Long userId) {

        // 팀 생성자와 삭제하려는 사람이 같아야 삭제가능

        Teams findTeam = teamRepository.findById(teamId).orElseThrow(NotFoundTeamExceptionId::new);

        if(!findTeam.getHostId().equals(userId)) {
            throw new CannotDeleteTeamException();
        }

        teamUserService.deleteTeamUser(teamId);
        teamTaskService.deleteTeamTask(teamId);
        teamRepository.delete(findTeam);

        return true;

    }

    public List<TeamDTO.TeamListResponse> getTeams() {

        return teamRepository.findAll().stream()
                .map(t -> TeamDTO.TeamListResponse.builder()
                        .teamId(t.getId())
                        .teamName(t.getTeamName())
                        .teamSize(t.getTeamSize())
                        .build())
                .collect(Collectors.toList());


    }


}
