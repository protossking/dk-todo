package com.dk.todo.domain.teams;

import com.dk.todo.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class TeamUsers extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "user_id")
    private Long userId;

    public TeamUsers() {

    }

    @Builder
    public TeamUsers(Long teamId, Long userId) {
        this.teamId = teamId;
        this.userId = userId;
    }


}
