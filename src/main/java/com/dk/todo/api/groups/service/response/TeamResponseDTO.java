package com.dk.todo.api.groups.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class TeamResponseDTO {


    public static class TeamAddResponse {

    }

    @Getter
    public static class TeamInviteResponse {

        private Long teamId;

        private List<String> emails;


    }


    @Getter
    @NoArgsConstructor
    public static class TeamJoinResponse {

        private Long teamId;

        private Long userId;

        @Builder
        public TeamJoinResponse(Long teamId, Long userId) {
            this.teamId = teamId;
            this.userId = userId;
        }
    }
}
