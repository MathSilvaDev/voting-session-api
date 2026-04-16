package com.matheus.voting_session_api.vote.service;

import com.matheus.voting_session_api.exception.votingsession.VotingSessionExpiredException;
import com.matheus.voting_session_api.member.entity.Member;
import com.matheus.voting_session_api.member.repository.MemberRepository;
import com.matheus.voting_session_api.vote.dto.request.VoteRequest;
import com.matheus.voting_session_api.vote.entity.Vote;
import com.matheus.voting_session_api.vote.repository.VoteRepository;
import com.matheus.voting_session_api.votingsession.entity.VotingSession;
import com.matheus.voting_session_api.votingsession.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final VotingSessionRepository votingSessionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void voteBySessionIdAndCpf(String cpf, Long sessionId, VoteRequest request){

        VotingSession votingSession = votingSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(!votingSession.isActive()){
            throw new VotingSessionExpiredException();
        }

        Member member = memberRepository.findByCpf(cpf)
                .orElseGet(() -> memberRepository.save(new Member(cpf)));

        Vote vote = new Vote(request.voteValue(), member, votingSession);

        member.addVote(vote);
        votingSession.addVote(vote);

        voteRepository.save(vote);
    }
}
