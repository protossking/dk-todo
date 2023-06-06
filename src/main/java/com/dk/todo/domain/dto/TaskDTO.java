package com.dk.todo.domain.dto;

import com.dk.todo.domain.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter

public class TaskDTO {


    @Getter
    public static class TaskRequest {

    }




    @Getter
    @AllArgsConstructor
    public static class TaskResponse {


        private Long taskId;

        private String title;

        private String titleEmoji;

        private String description;

        private TaskStatus status;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
        private LocalDateTime startedDt;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
        private LocalDateTime endedDt;
    }
}
