package com.matheus.voting_session_api.exception.global;

import com.matheus.voting_session_api.api.ApiError;
import com.matheus.voting_session_api.exception.votingsession.VotingSessionExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiError> toResponse(Object message, HttpStatus status){
        return ResponseEntity
                .status(status)
                .body(new ApiError(message, Instant.now()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> handleResponseStatus(ResponseStatusException e){
        HttpStatus httpStatus = HttpStatus.valueOf(e.getStatusCode().value());
        return toResponse(e.getReason(), httpStatus);
    }

    @ExceptionHandler(VotingSessionExpiredException.class)
    public ResponseEntity<ApiError> votingSessionExpired(VotingSessionExpiredException e){
        return toResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> internalException(Exception e){
        return toResponse("INTERNAL ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
