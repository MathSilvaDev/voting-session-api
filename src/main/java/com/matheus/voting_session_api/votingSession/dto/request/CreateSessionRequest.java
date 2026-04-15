package com.matheus.voting_session_api.votingSession.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public record CreateSessionRequest(
        @NotBlank
        String topic,

        @NotBlank
        String description,

        Instant startAt,
        Instant endAt
) { }
