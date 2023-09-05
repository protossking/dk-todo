package com.dk.todo.api.groups.service;

import com.dk.todo.api.groups.request.TeamRequestDTO;
import com.dk.todo.api.groups.service.response.TeamResponseDTO;
import com.dk.todo.api.invitation.service.InvitationService;
import com.dk.todo.domain.teams.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final InvitationService invitationService;

    private final TeamUserService teamUserService;
    private final TeamRepository teamRepository;


    @Transactional
    public TeamResponseDTO.TeamAddResponse addTeam(TeamRequestDTO.TeamAddRequest teamAddRequest, Long userId) {

        teamRepository.saveAndFlush(teamAddRequest.toEntity(userId));

        return null;

    }

    public TeamResponseDTO.TeamInviteResponse inviteTeam(TeamRequestDTO.TeamInviteRequest teamInviteRequest, Long userId) {

        invitationService.sendInvitation(userId, teamInviteRequest.getEmails(), teamInviteRequest.getTeamId());

        return null;

    }

    @Transactional
    public TeamResponseDTO.TeamJoinResponse joinTeam(TeamRequestDTO.TeamJoinRequest teamJoinRequest, Long userId) {

        Long teamId = invitationService.findTeamId(teamJoinRequest.getInviteCode());

        teamUserService.addTeamUser(teamId, userId);


        return TeamResponseDTO.TeamJoinResponse.builder()
                .teamId(teamId)
                .userId(userId)
                .build();

    }


}
