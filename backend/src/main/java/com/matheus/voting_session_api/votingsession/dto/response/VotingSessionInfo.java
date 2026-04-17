package com.matheus.voting_session_api.votingsession.dto.response;

public record VotingSessionInfo(
        VotingSessionResponse votingSession,
        long totalVotesQtd,
        long noVotesQtd,
        long yesVotesQtd
) { }
