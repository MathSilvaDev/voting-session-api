package com.matheus.voting_session_api.vote.service;

import com.matheus.voting_session_api.exception.votingsession.VotingSessionNotEnabledException;
import com.matheus.voting_session_api.member.entity.Member;
import com.matheus.voting_session_api.member.repository.MemberRepository;
import com.matheus.voting_session_api.vote.dto.request.VoteRequest;
import com.matheus.voting_session_api.vote.entity.Vote;
import com.matheus.voting_session_api.vote.enums.VoteValue;
import com.matheus.voting_session_api.vote.repository.VoteRepository;
import com.matheus.voting_session_api.votingsession.entity.VotingSession;
import com.matheus.voting_session_api.votingsession.repository.VotingSessionRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private VoteService voteService;

    @Nested
    class voteByCpfAndSessionId{

        @Test
        void shouldVoteSuccessfully(){
            VotingSession votingSession = new VotingSession(
                    "test-topic",
                    "test-description",
                    null,
                    null
            );

            String cpf = "000.000.000-00";
            Long sessionId = votingSession.getId();
            VoteRequest request = new VoteRequest(VoteValue.NO);

            Member member = new Member(cpf);
            Vote vote = new Vote(request.voteValue(), member, votingSession);

            when(votingSessionRepository.findById(sessionId))
                    .thenReturn(Optional.of(votingSession));

            when(memberRepository.findByCpf(Member.normalizeCpf(cpf)))
                    .thenReturn(Optional.of(member));

            when(voteRepository.save(any(Vote.class)))
                    .thenReturn(vote);

            voteService.voteByCpfAndSessionId(cpf, sessionId, request);

            assertEquals(Member.normalizeCpf(cpf), vote.getMember().getCpf());

            verify(votingSessionRepository).findById(sessionId);
            verify(memberRepository).findByCpf(Member.normalizeCpf(cpf));
            verify(voteRepository).save(any(Vote.class));
        }

        @Test
        void shouldThrowVotingSessionNotFound(){
            String cpf = "000.000.000-00";
            Long sessionId = 1L;
            VoteRequest request = new VoteRequest(VoteValue.NO);

            when(votingSessionRepository.findById(sessionId))
                    .thenReturn(Optional.empty());

            assertThrows(ResponseStatusException.class,
                    () -> voteService.voteByCpfAndSessionId(cpf, sessionId, request));
        }

        @Test
        void shouldThrowVotingSessionNotEnabled(){
            VotingSession votingSession = new VotingSession(
                    "test-topic",
                    "test-description",
                    Instant.now().plusSeconds(60),
                    null
            );

            String cpf = "000.000.000-00";
            Long sessionId = votingSession.getId();
            VoteRequest request = new VoteRequest(VoteValue.NO);

            when(votingSessionRepository.findById(sessionId))
                    .thenReturn(Optional.of(votingSession));

            assertThrows(VotingSessionNotEnabledException.class,
                    () -> voteService.voteByCpfAndSessionId(cpf, sessionId, request));

        }
    }
}