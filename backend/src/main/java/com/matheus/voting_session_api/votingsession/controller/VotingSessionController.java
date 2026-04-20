package com.matheus.voting_session_api.votingsession.controller;

import com.matheus.voting_session_api.votingsession.dto.request.CreateSessionRequest;
import com.matheus.voting_session_api.votingsession.dto.response.VotingSessionInfo;
import com.matheus.voting_session_api.votingsession.dto.response.VotingSessionResponse;
import com.matheus.voting_session_api.votingsession.service.VotingSessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
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
    public ResponseEntity<List<VotingSessionResponse>> findAll(){
        return ResponseEntity.ok(
                votingSessionService.findAll()
        );
    }

    @GetMapping("/activated")
    public ResponseEntity<List<VotingSessionResponse>> findAllActivated(){
        return ResponseEntity.ok(
                votingSessionService.findAllActivated()
        );
    }
}
