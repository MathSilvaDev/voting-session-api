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

    @Column(nullable = false)
    private Instant startAt;

    @Column(nullable = false)
    private Instant endAt;

    @OneToMany(mappedBy = "votingSession")
    private Set<Vote> votes = new HashSet<>();

    public VotingSession(String topic, String description, Instant startAt, Instant endAt){
        this.topic = topic;
        this.description = description;
        verifyDate(startAt, endAt);
    }

    public boolean isActive(){
        Instant now = Instant.now();

        return now.isAfter(startAt) && now.isBefore(endAt);
    }

    private void verifyDate(Instant startAt, Instant endAt){
        Instant now = Instant.now();

        if(startAt == null && endAt == null){
            this.startAt = now.plusSeconds(1);
            this.endAt = this.startAt.plusSeconds(60);
            return;
        }else if(startAt == null){
            startAt = now.plusSeconds(1);
        }else if (endAt == null) {
            endAt = startAt.plusSeconds(60);
        }

        boolean isValid = now.isBefore(startAt) && startAt.isBefore(endAt);

        if (isValid){
            this.startAt = startAt;
            this.endAt = endAt;
        }else{
            this.startAt = null;
            this.endAt = null;
        }
    }
}
