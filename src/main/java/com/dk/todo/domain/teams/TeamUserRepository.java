package com.dk.todo.domain.teams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamUserRepository extends JpaRepository<TeamUsers, Long> {


    @Modifying
    void deleteAllByTeamId(Long teamId);
}
