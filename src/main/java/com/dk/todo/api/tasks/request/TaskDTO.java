package com.dk.todo.api.tasks.request;

import com.dk.todo.domain.tasks.Task;
import com.dk.todo.domain.users.Users;
import com.dk.todo.domain.tasks.TaskStatus;
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
                    .startedAt(this.startedAt)
                    .endedAt(this.endedAt)
                    .titleEmoji(this.titleEmoji)
                    .status(TaskStatus.TODO)
                    .backgroundColor(this.backgroundColor)
                    .users(users)
                    .build();
        }

    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskUpdateRequest {
        private String title;
        private String description;
        private LocalDateTime startedAt;
        private LocalDateTime endedAt;
        private String titleEmoji;
        private String backgroundColor;
    }



    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskStatusUpdateRequest {

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
    public static class TaskStatusUpdateResponse {

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
