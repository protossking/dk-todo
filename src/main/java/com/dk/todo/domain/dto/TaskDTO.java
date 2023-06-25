package com.dk.todo.domain.dto;

import com.dk.todo.domain.Task;
import com.dk.todo.domain.Users;
import com.dk.todo.domain.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter

public class TaskDTO {


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskRequest {
        private String title;
        private String description;
        private LocalDateTime startedAt;
        private LocalDateTime endedAt;
        private String titleEmoji;
        private String backgroundColor;


        public  Task toEntity(Users users) {
            return Task.builder()
                    .title(this.title)
                    .description(this.description)
                    .startedDt(this.startedAt)
                    .endedDt(this.endedAt)
                    .titleEmoji(this.titleEmoji)
                    .status(TaskStatus.TODO)
                    .users(users)
                    .build();
        }

    }




    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskUpdateRequest {

        private String taskStatus;
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskResponse {


        private Long taskId;

        private String title;

        private String titleEmoji;

        private String description;

        private TaskStatus taskStatus;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
        private LocalDateTime startedAt;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
        private LocalDateTime endedAt;

        private Boolean isBookmarked;

        private String backgroundColor;
    }

    @Getter
    @AllArgsConstructor
    public static class TaskUpdateResponse {

        private Long taskId;
        private TaskStatus taskStatus;
    }

    @Getter
    @AllArgsConstructor
    public static class TaskDeleteResponse {
        private Long taskId;

    }

    @Getter
    @AllArgsConstructor
    public static class TaskBookmarkUpdateResponse {
        private Long taskId;
        private Boolean isBookmark;
    }
}
