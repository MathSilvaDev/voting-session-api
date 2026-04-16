package com.matheus.voting_session_api.api;

import java.time.Instant;

public record ApiError(
        Object message,
        Instant timestamp
) { }
