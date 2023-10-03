package com.dk.todo.api.teams.request;

import com.dk.todo.domain.teams.Teams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


public class TeamRequestDTO {




    @Getter
    public static class TeamAddRequest {

        @Schema(description = "그룹 이름")
        @NotBlank
        private String teamName;


        @Schema(description = "그룹 인원수")
        @Min(value = 2)
        private int teamSize;

        @Schema(description = "그룹 소개글")
        private String introduce;


        public Teams toEntity(Long userId) {

            return Teams.builder()
                    .teamName(this.teamName)
                    .teamSize(this.teamSize)
                    .hostId(userId)
                    .introduce(this.introduce)
                    .build();
        }

        public Teams toEntity(Long userId, String fileUrl) {

            return Teams.builder()
                    .teamName(this.teamName)
                    .teamSize(this.teamSize)
                    .hostId(userId)
                    .introduce(this.introduce)
                    .fileUrl(fileUrl)
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
