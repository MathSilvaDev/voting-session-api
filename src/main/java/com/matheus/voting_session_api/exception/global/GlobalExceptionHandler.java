package com.matheus.voting_session_api.exception.global;

import com.matheus.voting_session_api.api.ApiError;
import com.matheus.voting_session_api.exception.votingsession.VotingSessionExpiredException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiError> toResponse(Object message){
        return ResponseEntity
                .badRequest()
                .body(new ApiError(message, Instant.now()));
    }

    @ExceptionHandler(VotingSessionExpiredException.class)
    public ResponseEntity<ApiError> votingSessionExpired(VotingSessionExpiredException e){
        return toResponse(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> internalException(Exception e){
        return toResponse("INTERNAL ERROR");
    }
}
