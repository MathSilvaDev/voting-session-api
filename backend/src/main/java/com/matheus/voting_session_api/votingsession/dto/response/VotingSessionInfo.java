package com.matheus.voting_session_api.votingsession.dto.response;

public record VotingSessionInfo(
        long totalVotes,
        long noVotes,
        long yesVotes
) { }
