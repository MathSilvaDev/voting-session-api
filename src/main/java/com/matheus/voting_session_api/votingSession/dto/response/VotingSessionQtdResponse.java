package com.matheus.voting_session_api.votingSession.dto.response;

public record VotingSessionQtdResponse(
        int allQuantity,
        int yesQuantity,
        int noQuantity
) { }
