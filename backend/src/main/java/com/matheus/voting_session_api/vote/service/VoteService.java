package com.matheus.voting_session_api.vote.service;

import com.matheus.voting_session_api.exception.votingsession.VotingSessionNotEnabledException;
import com.matheus.voting_session_api.member.entity.Member;
import com.matheus.voting_session_api.member.repository.MemberRepository;
import com.matheus.voting_session_api.vote.dto.request.VoteRequest;
import com.matheus.voting_session_api.vote.entity.Vote;
import com.matheus.voting_session_api.vote.repository.VoteRepository;
import com.matheus.voting_session_api.votingsession.entity.VotingSession;
import com.matheus.voting_session_api.votingsession.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final VotingSessionRepository votingSessionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void voteByCpfAndSessionId(String cpf, Long sessionId, VoteRequest request){

        Instant now = Instant.now();

        VotingSession votingSession = votingSessionRepository.findById(sessionId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Session Not Found"));

        if(!votingSession.isActive()){
            if(now.isBefore(votingSession.getStartAt())){
                throw new VotingSessionNotEnabledException("Voting session has not started yet");
            }

            if(now.isAfter(votingSession.getEndAt())){
                throw new VotingSessionNotEnabledException("Voting session has already ended");
            }
        }

        Member member = memberRepository.findByCpf(Member.normalizeCpf(cpf))
                .orElseGet(() -> memberRepository.save(new Member(cpf)));

        Vote vote = new Vote(request.voteValue(), member, votingSession);

        try {
            voteRepository.save(vote);
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user already voted");
        }
    }
}
