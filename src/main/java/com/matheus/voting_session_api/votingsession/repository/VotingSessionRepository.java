package com.matheus.voting_session_api.votingsession.repository;

import com.matheus.voting_session_api.votingsession.entity.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {
}
