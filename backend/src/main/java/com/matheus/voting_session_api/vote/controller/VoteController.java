package com.matheus.voting_session_api.vote.controller;

import com.matheus.voting_session_api.vote.dto.request.VoteRequest;
import com.matheus.voting_session_api.vote.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("{cpf}/sessions/{id}")
    public ResponseEntity<Void> voteByCpfAndSessionId(
            @PathVariable String cpf, @PathVariable("id") Long sessionId,
            @Valid @RequestBody VoteRequest request){

        voteService.voteByCpfAndSessionId(cpf, sessionId, request);
        return ResponseEntity.noContent().build();
    }

}
