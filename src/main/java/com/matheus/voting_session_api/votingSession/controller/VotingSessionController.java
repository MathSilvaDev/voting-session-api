package com.matheus.voting_session_api.votingSession.controller;

import com.matheus.voting_session_api.votingSession.dto.request.CreateSessionRequest;
import com.matheus.voting_session_api.votingSession.dto.response.VotingSessionInfo;
import com.matheus.voting_session_api.votingSession.dto.response.VotingSessionResponse;
import com.matheus.voting_session_api.votingSession.service.VotingSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<VotingSessionInfo>> findAll(){
        return ResponseEntity.ok(
                votingSessionService.findAll()
        );
    }

    @GetMapping("/activated")
    public ResponseEntity<List<VotingSessionInfo>> findAllActivated(){
        return ResponseEntity.ok(
                votingSessionService.findAllActivated()
        );
    }
}
