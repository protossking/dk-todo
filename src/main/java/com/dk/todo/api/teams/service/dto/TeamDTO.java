package com.dk.todo.api.teams.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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



}
