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

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "file_url")
    private String fileUrl;

    public Teams() {

    }

    @Builder
    public Teams(String teamName, Integer teamSize, Long hostId, String fileUrl, String introduce) {
        this.teamName = teamName;
        this.teamSize = teamSize;
        this.hostId = hostId;
        this.fileUrl = fileUrl;
        this.introduce = introduce;
    }
}
