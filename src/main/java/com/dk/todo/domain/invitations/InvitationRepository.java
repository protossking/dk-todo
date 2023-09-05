package com.dk.todo.domain.invitations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitations, Long> {

    boolean existsBySenderIdAndToEmailAndTeamId(Long senderId, String toEmail, Long teamId);

    Optional<Invitations> findBySenderIdAndToEmailAndTeamId(Long senderId, String toEmail, Long teamId);

    @Modifying
    @Query("update Invitations i " +
            "set i.inviteCode = :inviteCode " +
            "where i.id = :id")
    void renewInviteCode(@Param(value = "inviteCode") String inviteCode, @Param(value = "id") Long invitationId);

    Optional<Invitations> findByInviteCode(String inviteCode);





}
