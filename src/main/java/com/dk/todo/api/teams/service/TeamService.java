package com.dk.todo.api.teams.service;

import com.dk.todo.api.teams.request.TeamRequestDTO;
import com.dk.todo.api.teams.service.dto.TeamDTO;
import com.dk.todo.api.invitation.service.InvitationService;
import com.dk.todo.domain.exception.teams.CannotDeleteTeamException;
import com.dk.todo.domain.exception.teams.CannotExitTeamException;
import com.dk.todo.domain.exception.teams.NotFoundTeamIdException;
import com.dk.todo.domain.exception.users.NotFoundUserIdException;
import com.dk.todo.domain.teams.TeamRepository;
import com.dk.todo.domain.teams.Teams;
import com.dk.todo.domain.users.UserRepository;
import com.dk.todo.domain.users.Users;
import com.dk.todo.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final InvitationService invitationService;
    private final TeamUserService teamUserService;

    private final TeamRepository teamRepository;

    private final UserRepository userRepository;

    private final FileUtils fileUtils;


    @Transactional
    public TeamDTO.TeamAddResponse addTeam(TeamRequestDTO.TeamAddRequest teamAddRequest, MultipartFile multipartFile, Long userId) {

        Teams savedTeam = null;

        if(!ObjectUtils.isEmpty(multipartFile)) {

            String fileUrl = fileUtils.fileUpload(multipartFile, userId);
            savedTeam = teamRepository.saveAndFlush(teamAddRequest.toEntity(userId, fileUrl));
        }
        else {
            savedTeam = teamRepository.saveAndFlush(teamAddRequest.toEntity(userId));
        }

        teamUserService.addTeamUser(savedTeam.getId(), userId);

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

        Teams findTeam = teamRepository.findById(teamId).orElseThrow(NotFoundTeamIdException::new);

        if(!findTeam.getHostId().equals(userId)) {
            throw new CannotDeleteTeamException();
        }

        teamUserService.deleteTeamUserByTeamId(teamId);
        teamRepository.delete(findTeam);

        return true;

    }

    @Transactional
    public Boolean exitTeam(Long teamId, Long userId) {

        Teams findTeam = teamRepository.findById(teamId).orElseThrow(NotFoundTeamIdException::new);

        if(findTeam.getHostId().equals(userId)) {
            throw new CannotExitTeamException();
        }

        /*
        팀유저에서 해당 유저 이름 찾아 삭제
         */

        teamUserService.deleteTeamUserByUserId(userId);

        return true;

    }


    public TeamDTO.TeamDetailResponse getTeam(Long teamId) {

        Teams findTeam = teamRepository.findById(teamId).orElseThrow(NotFoundTeamIdException::new);

        String hostName = userRepository.findById(findTeam.getHostId()).orElseThrow(NotFoundUserIdException::new).getName();

        List<TeamDTO.TeamUserListResponse> teamUserList = teamUserService.findUserByTeamId(findTeam.getId());

        List<TeamDTO.TeamUserListResponse> excludeHostUserList = teamUserList.stream().filter(u -> !u.getUserId().equals(findTeam.getHostId())).collect(Collectors.toList());

        return TeamDTO.TeamDetailResponse.builder()
                .teamId(findTeam.getId())
                .teamName(findTeam.getTeamName())
                .teamSize(findTeam.getTeamSize())
                .introduce(findTeam.getIntroduce())
                .hostName(hostName)
                .fileUrl(findTeam.getFileUrl())
                .teamUserList(excludeHostUserList)
                .build();

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
