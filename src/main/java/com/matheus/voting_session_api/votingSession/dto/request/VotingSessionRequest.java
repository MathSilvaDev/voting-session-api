package com.matheus.voting_session_api.votingSession.dto.request;

import java.time.Instant;

public record VotingSessionRequest(
        String topic,
        String description,
        Instant startAt,
        Instant endAt
) { }
