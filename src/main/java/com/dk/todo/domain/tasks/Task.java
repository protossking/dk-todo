package com.dk.todo.domain.tasks;

import com.dk.todo.api.tasks.request.TaskDTO;
import com.dk.todo.domain.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Entity
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String titleEmoji;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private String backgroundColor;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Boolean isBookmark;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users users;


    public void updateTaskStatus(String taskStatus) {
        this.status = TaskStatus.valueOf(taskStatus);
    }

    public void updateBookmark() {
        this.isBookmark = !isBookmark;
    }

    public void updateTask(TaskDTO.TaskUpdateRequest taskUpdateRequest) {
        this.title = taskUpdateRequest.getTitle();
        this.description = taskUpdateRequest.getDescription();
        this.startedAt = taskUpdateRequest.getStartedAt();
        this.endedAt = taskUpdateRequest.getEndedAt();
        this.titleEmoji = taskUpdateRequest.getTitleEmoji();
        this.backgroundColor = taskUpdateRequest.getBackgroundColor();
    }

}
