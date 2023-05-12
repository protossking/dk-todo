package com.dk.todo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
public class TaskAddRequestDTO {



    @Schema(description = "대표 이모지")
    private String emoji;

    @Schema(description = "제목")
    @NotEmpty
    private String title;

    @Schema(description = "내용")
    private String description;

    @Schema(description = "시작예정일")
    private LocalDateTime startedAt;

    @Schema(description = "종료예정일")
    private LocalDateTime endedAt;

    @Schema(description = "상태 (TODO), (DOING), (DONE)")
    private String status;





}
