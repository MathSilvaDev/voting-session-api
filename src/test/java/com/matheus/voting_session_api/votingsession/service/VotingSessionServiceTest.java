package com.matheus.voting_session_api.votingsession.service;

import com.matheus.voting_session_api.votingsession.dto.request.CreateSessionRequest;
import com.matheus.voting_session_api.votingsession.dto.response.VotingSessionResponse;
import com.matheus.voting_session_api.votingsession.entity.VotingSession;
import com.matheus.voting_session_api.votingsession.repository.VotingSessionRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotingSessionServiceTest {

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @InjectMocks
    private VotingSessionService votingSessionService;

    @Nested
    class Create{

        @Test
        void shouldCreateSuccessfully(){

            CreateSessionRequest request = new CreateSessionRequest(
                    "test-topic",
                    "test-description",
                    null,
                    null
            );

            VotingSession votingSession = new VotingSession(
                    request.topic(),
                    request.description(),
                    request.startAt(),
                    request.endAt()
            );

            when(votingSessionRepository.save(any(VotingSession.class)))
                    .thenReturn(votingSession);

            VotingSessionResponse response = votingSessionService.create(request);

            assertEquals(request.topic(), response.topic());
            assertEquals(request.description(), response.description());
            assertTrue(response.isActivated());

            verify(votingSessionRepository).save(any(VotingSession.class));
        }
    }

}