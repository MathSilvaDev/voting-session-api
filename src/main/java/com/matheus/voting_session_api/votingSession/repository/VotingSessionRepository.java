package com.matheus.voting_session_api.votingSession.repository;

import com.matheus.voting_session_api.votingSession.entity.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {
}
