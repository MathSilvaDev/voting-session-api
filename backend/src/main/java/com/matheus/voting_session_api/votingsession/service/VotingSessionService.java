package com.matheus.voting_session_api.votingsession.service;

import com.matheus.voting_session_api.vote.enums.VoteValue;
import com.matheus.voting_session_api.votingsession.dto.request.CreateSessionRequest;
import com.matheus.voting_session_api.votingsession.dto.response.VotingSessionInfo;
import com.matheus.voting_session_api.votingsession.dto.response.VotingSessionResponse;
import com.matheus.voting_session_api.votingsession.entity.VotingSession;
import com.matheus.voting_session_api.votingsession.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VotingSessionService {

    private final VotingSessionRepository votingSessionRepository;

    public VotingSessionResponse create(CreateSessionRequest request){

        VotingSession votingSession = new VotingSession(
                request.topic(),
                request.description(),
                request.startAt(),
                request.endAt()
        );

        votingSessionRepository.save(votingSession);

        return toResponse(votingSession);
    }

    public List<VotingSessionResponse> findAll(){

        return votingSessionRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<VotingSessionResponse> findAllActivated(){

        Instant now = Instant.now();

        return votingSessionRepository
                .findByStartAtBeforeAndEndAtAfter(now, now)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private VotingSessionResponse toResponse(VotingSession votingSession){
        return new VotingSessionResponse(
                votingSession.getId(),
                votingSession.getTopic(),
                votingSession.getDescription(),
                votingSession.getStartAt(),
                votingSession.getEndAt(),
                votingSession.isActive(),
                toResponseInfo(votingSession)
        );
    }

    private VotingSessionInfo toResponseInfo(VotingSession votingSession){
        long totalVotes = votingSession.getVotes().size();

        long noVotes = votingSession.getVotes()
                .stream()
                .filter(v -> v.getVoteValue() == VoteValue.NO)
                .count();

        long yesVotes = votingSession.getVotes()
                .stream()
                .filter(v -> v.getVoteValue() == VoteValue.YES)
                .count();

        return new VotingSessionInfo(
                totalVotes,
                noVotes,
                yesVotes
        );
    }
}
