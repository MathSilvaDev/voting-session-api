package com.matheus.voting_session_api.votingSession.entity;

import com.matheus.voting_session_api.vote.entity.Vote;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "voting_session")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VotingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "votingSession")
    private Set<Vote> votes = new HashSet<>();

    public VotingSession(String topic, String description){
        this.topic = topic;
        this.description = description;
    }
}
