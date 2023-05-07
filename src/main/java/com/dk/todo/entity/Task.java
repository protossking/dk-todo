package com.dk.todo.entity;

import com.dk.todo.entity.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task")
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("제목")
    @Column(name = "title")
    private String title;

    @Comment("내용")
    @Column(name = "description")
    private String description;

    @Comment("시작일")
    @Column(name = "started_at")
    private LocalDate startedAt;

    @Comment("마감일")
    @Column(name = "ended_at")
    private LocalDate endedAt;

    @Comment("할일 상태")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


}
