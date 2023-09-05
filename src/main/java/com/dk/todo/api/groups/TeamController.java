package com.dk.todo.api.groups;

import com.dk.todo.api.groups.request.TeamRequestDTO;
import com.dk.todo.api.groups.service.TeamService;
import com.dk.todo.api.groups.service.response.TeamResponseDTO;
import com.dk.todo.config.oauth.dto.SessionUser;
import com.dk.todo.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;


    @Operation(summary = "팀(그룹) 생성", description = "팀(그룹)을 생성한다.")
    @PostMapping("")
    public ApiResponse<TeamResponseDTO.TeamAddResponse> postTeam(@RequestBody @Valid TeamRequestDTO.TeamAddRequest teamAddRequest, @Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {

        return ApiResponse.createSuccess(teamService.addTeam(teamAddRequest, sessionUser.getId()));
    }

    @Operation(summary = "팀(그룹) 초대", description = "팀(그룹)에 초대한다.")
    @PostMapping("/invite")
    public ApiResponse<TeamResponseDTO.TeamInviteResponse> inviteTeam(@RequestBody @Valid TeamRequestDTO.TeamInviteRequest teamInviteRequest, @Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {

        return ApiResponse.createSuccess(teamService.inviteTeam(teamInviteRequest, sessionUser.getId()));
    }

    @Operation(summary = "팀(그룹) 참여", description = "팀(그룹)을 참여한다.")
    @PostMapping("/join")
    public ApiResponse<TeamResponseDTO.TeamJoinResponse> joinTeam(@RequestBody @Valid TeamRequestDTO.TeamJoinRequest teamJoinRequest, @Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {

        return ApiResponse.createSuccess(teamService.joinTeam(teamJoinRequest, sessionUser.getId()));
    }


}
