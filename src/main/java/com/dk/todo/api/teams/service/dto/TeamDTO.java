package com.dk.todo.api.teams.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.List;

public class TeamDTO {




    @Getter
    public static class TeamInviteResponse {

        private Long teamId;

        private List<String> emails;


    }

    @Getter
    public static class TeamAddResponse {

        @Schema(description = "생성된 팀(그룹) ID")
        private Long teamId;

        @Schema(description = "생성된 팀(그룹) 이름")
        private String teamName;

        @Builder
        public TeamAddResponse(Long teamId, String teamName) {
            this.teamId = teamId;
            this.teamName = teamName;
        }
    }


    @Getter
    @NoArgsConstructor
    public static class TeamJoinResponse {

        @Schema(description = "팀(그룹) ID")
        private Long teamId;

        @Schema(description = "회원 ID")
        private Long userId;

        @Builder
        public TeamJoinResponse(Long teamId, Long userId) {
            this.teamId = teamId;
            this.userId = userId;
        }
    }

    @Getter
    public static class TeamListResponse {

        @Schema(description = "팀(그룹) ID")
        private Long teamId;

        @Schema(description = "팀(그룹) 이름")
        private String teamName;

        @Schema(description = "팀(그룹) 크기")
        private int teamSize;

        @Builder
        public TeamListResponse(Long teamId, String teamName, int teamSize) {
            this.teamId = teamId;
            this.teamName = teamName;
            this.teamSize = teamSize;
        }
    }

    @Getter
    public static class TeamDetailResponse {

        @Schema(description = "팀(그룹) ID")
        private Long teamId;

        @Schema(description = "팀(그룹) 이름")
        private String teamName;

        @Schema(description = "팀(그룹) 인원")
        private long teamSize;

        @Schema(description = "팀(그룹) 설명")
        private String introduce;

        @Schema(description = "팀(그룹)장 이름")
        private String hostName;

        @Schema(description = "파일 url")
        private String fileUrl;

        @Schema(description = "팀(그룹)원 정보")
        private List<TeamUserListResponse> teamUserList;

        @Builder
        public TeamDetailResponse(Long teamId, String teamName, long teamSize, String introduce, String hostName, String fileUrl, List<TeamUserListResponse> teamUserList) {
            this.teamId = teamId;
            this.teamName = teamName;
            this.teamSize = teamSize;
            this.introduce = introduce;
            this.hostName = hostName;
            this.fileUrl = fileUrl;
            this.teamUserList = teamUserList;
        }
    }

    @Getter
    public static class TeamUserListResponse {

        @Schema(description = "유저 Id")
        private Long userId;
        @Schema(description = "이름")
        private String name;

        @Schema(description = "이메일")
        private String email;

        @Builder
        public TeamUserListResponse(Long userId, String name, String email) {
            this.userId = userId;
            this.name = name;
            this.email = email;
        }
    }



}
