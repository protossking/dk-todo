package com.dk.todo.domain.dto;

import com.dk.todo.domain.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter

public class TaskDTO {


    @Getter
    public static class TaskRequest {

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
