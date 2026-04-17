package com.matheus.voting_session_api.vote.dto.request;

import com.matheus.voting_session_api.vote.enums.VoteValue;
import jakarta.validation.constraints.NotNull;

public record VoteRequest(
        @NotNull
        VoteValue voteValue
) { }
