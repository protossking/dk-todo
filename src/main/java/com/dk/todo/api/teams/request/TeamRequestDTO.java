package com.dk.todo.api.teams.request;

import com.dk.todo.domain.teams.Teams;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


public class TeamRequestDTO {




    @Getter
    public static class TeamAddRequest {

        @NotBlank
        private String teamName;


        @Min(value = 2)
        private int teamSize;


        public Teams toEntity(Long userId) {

            return Teams.builder()
                    .teamName(this.teamName)
                    .teamSize(this.teamSize)
                    .hostId(userId)
                    .build();
        }
    }

    @Getter
    public static class TeamInviteRequest {

        @NotNull
        private Long teamId;

        @Size(min = 1)
        private List<String> emails;

    }

    @Getter
    public static class TeamJoinRequest {

        @NotBlank
        private String inviteCode;
    }

}
