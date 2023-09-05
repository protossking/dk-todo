package com.dk.todo.domain.teams;

import com.dk.todo.domain.BaseEntity;
import com.dk.todo.domain.tasks.TaskStatus;
import lombok.Getter;
import org.joda.time.LocalDateTime;


import javax.persistence.*;

@Getter
@Entity
public class TeamTasks extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "titleEmoji")
    private String titleEmoji;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "backgroup_color")
    private String backgroundColor;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Column(name = "is_bookmark")
    private Boolean isBookmark;


}
