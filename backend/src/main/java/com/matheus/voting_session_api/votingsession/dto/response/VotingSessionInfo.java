package com.matheus.voting_session_api.votingsession.dto.response;

public record VotingSessionInfo(
        VotingSessionResponse votingSession,
        long totalVotes,
        long noVotes,
        long yesVotes
) { }
