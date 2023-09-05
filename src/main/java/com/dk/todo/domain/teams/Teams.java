package com.dk.todo.domain.teams;

import com.dk.todo.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Teams extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "team_size", nullable = false)
    private Integer teamSize;

    @Column(name = "host_id")
    private Long hostId;

    public Teams() {

    }

    @Builder
    public Teams(String teamName, Integer teamSize, Long hostId) {
        this.teamName = teamName;
        this.teamSize = teamSize;
        this.hostId = hostId;
    }
}
