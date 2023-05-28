package com.dk.todo.domain;

import com.dk.todo.domain.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), insertable = false, updatable = false)
    private Users users;

    @Column(name = "user_id")
    private Long userId;

}
