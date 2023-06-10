package com.dk.todo.domain;

import com.dk.todo.domain.enums.TaskStatus;
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

    private LocalDateTime startedDt;

    private LocalDateTime endedDt;

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

}
