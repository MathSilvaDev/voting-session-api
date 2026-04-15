package com.matheus.voting_session_api.votingSession.dto.response;

import java.time.Instant;

public record VotingSessionResponse(
        Long id,
        String topic,
        String description,
        Instant startAt,
        Instant endAt
) { }
