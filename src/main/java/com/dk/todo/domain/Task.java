package com.dk.todo.domain;

import com.dk.todo.domain.enums.TaskType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String titleEmoji;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    private LocalDateTime startedDt;

    private LocalDateTime endedDt;



    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users member;

}
