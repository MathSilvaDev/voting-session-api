package com.matheus.voting_session_api.votingsession.repository;

import com.matheus.voting_session_api.votingsession.entity.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {

    List<VotingSession> findByStartAtBeforeAndEndAtAfter(Instant now1, Instant now2);
}
