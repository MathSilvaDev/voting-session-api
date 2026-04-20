package com.matheus.voting_session_api.votingsession.dto.response;

import java.time.Instant;

public record VotingSessionResponse(
        Long id,
        String topic,
        String description,
        Instant startAt,
        Instant endAt,
        boolean isActivated,
        VotingSessionInfo votingSessionInfo
) { }
