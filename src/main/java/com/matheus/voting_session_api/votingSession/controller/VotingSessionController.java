package com.matheus.voting_session_api.votingSession.controller;

import com.matheus.voting_session_api.votingSession.dto.request.CreateSessionRequest;
import com.matheus.voting_session_api.votingSession.dto.response.VotingSessionResponse;
import com.matheus.voting_session_api.votingSession.service.VotingSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/voting-session")
@RequiredArgsConstructor
public class VotingSessionController {

    private final VotingSessionService votingSessionService;

    @PostMapping
    public ResponseEntity<VotingSessionResponse> create(
            @Valid @RequestBody CreateSessionRequest request){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(votingSessionService.create(request));
    }
}
