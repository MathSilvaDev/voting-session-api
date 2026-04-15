package com.matheus.voting_session_api.votingSession.service;

import com.matheus.voting_session_api.votingSession.dto.request.CreateSessionRequest;
import com.matheus.voting_session_api.votingSession.dto.response.VotingSessionResponse;
import com.matheus.voting_session_api.votingSession.entity.VotingSession;
import com.matheus.voting_session_api.votingSession.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotingSessionService {

    private final VotingSessionRepository votingSessionrepository;

    public VotingSessionResponse create(CreateSessionRequest request){

        VotingSession votingSession = new VotingSession(
                request.topic(),
                request.description(),
                request.startAt(),
                request.endAt()
        );

        votingSessionrepository.save(votingSession);

        return toResponse(votingSession);
    }

    private VotingSessionResponse toResponse(VotingSession votingSession){
        return new VotingSessionResponse(
                votingSession.getId(),
                votingSession.getTopic(),
                votingSession.getDescription(),
                votingSession.getStartAt(),
                votingSession.getEndAt()
        );
    }
}
