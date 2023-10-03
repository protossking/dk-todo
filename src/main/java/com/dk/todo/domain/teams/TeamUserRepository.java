package com.dk.todo.domain.teams;

import com.dk.todo.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamUserRepository extends JpaRepository<TeamUsers, Long> {


    @Modifying
    void deleteAllByTeamId(Long teamId);

    @Modifying
    void deleteByUserId(Long userId);

    @Query("SELECT u FROM TeamUsers  tu JOIN Users  u ON tu.userId = u.id WHERE tu.teamId = :teamId")
    List<Users> findUserByTeamId(@Param("teamId") Long teamId);

}
