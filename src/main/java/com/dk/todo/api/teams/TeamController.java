package com.dk.todo.api.teams;

import com.dk.todo.api.teams.request.TeamRequestDTO;
import com.dk.todo.api.teams.service.TeamService;
import com.dk.todo.api.teams.service.dto.TeamDTO;
import com.dk.todo.config.oauth.dto.SessionUser;
import com.dk.todo.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;


    @Operation(summary = "팀(그룹) 생성", description = "팀(그룹)을 생성한다.")
    @PostMapping("")
    public ApiResponse<TeamDTO.TeamAddResponse> postTeam(@RequestBody @Valid TeamRequestDTO.TeamAddRequest teamAddRequest, @Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {

        return ApiResponse.createSuccess(teamService.addTeam(teamAddRequest, sessionUser.getId()));
    }

    @Operation(summary = "팀(그룹) 초대", description = "팀(그룹)에 초대한다.")
    @PostMapping("/invite")
    public ApiResponse<TeamDTO.TeamInviteResponse> inviteTeam(@RequestBody @Valid TeamRequestDTO.TeamInviteRequest teamInviteRequest, @Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {

        return ApiResponse.createSuccess(teamService.inviteTeam(teamInviteRequest, sessionUser.getId()));
    }

    @Operation(summary = "팀(그룹) 참여", description = "팀(그룹)을 참여한다.")
    @PostMapping("/join")
    public ApiResponse<TeamDTO.TeamJoinResponse> joinTeam(@RequestBody @Valid TeamRequestDTO.TeamJoinRequest teamJoinRequest, @Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {

        return ApiResponse.createSuccess(teamService.joinTeam(teamJoinRequest, sessionUser.getId()));
    }

    @Operation(summary = "팀(그룹) 목록 조회", description = "팀(그룹) 목록 조회한다.")
    @GetMapping("")
    public ApiResponse<List<TeamDTO.TeamListResponse>> getTeams() {

        return ApiResponse.createSuccess(teamService.getTeams());

    }

    @Operation(summary = "팀(그룹) 상세조회", description = "이름, 그룹장이름, 그룹 설명, 그룹인원, 그룹원 정보(이름, 이메일), 그룹 대표 이미지")
    @GetMapping("/{id}")
    public ApiResponse<Boolean> getTeam(@PathVariable Long teamId) {
        return null;
    }

    @Operation(summary = "팀(그룹) 삭제", description = "팀(그룹) 삭제 - 그룹장만이 삭제할 수 있다.")
    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> deleteTeam(@PathVariable Long teamId, @Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {

        return ApiResponse.createSuccess(teamService.deleteTeam(teamId, sessionUser.getId()));
    }

    @Operation(summary = "팀(그룹) 탈퇴", description = "팀(그룹) 탈퇴 - 그룹장은 탈퇴할 수 없음.")
    @PostMapping("/{id}")
    public ApiResponse<Boolean> exitTeam(@PathVariable Long teamId, @Parameter(hidden = true) @AuthenticationPrincipal SessionUser sessionUser) {
        return null;
    }





}
