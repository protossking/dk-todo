package com.dk.todo.domain.dto;

import com.dk.todo.domain.Task;
import com.dk.todo.domain.Users;
import com.dk.todo.domain.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskAddRequestDTO {

    private String title;

    private String description;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private String titleEmoji;

    private String backgroundColor;


    public Task toEntity(Users users) {

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
