package com.matheus.voting_session_api.votingsession.entity;

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

    public void addVote(Vote vote){
        votes.add(vote);
    }

    public boolean isActive(){
        Instant now = Instant.now();
        return startAt.isBefore(now) && endAt.isAfter(now);
    }

    private void verifyDate(Instant startAt, Instant endAt){
        Instant now = Instant.now();
        Instant nowMinusSecond = now.minusSeconds(1);

        if(startAt == null && endAt == null){
            this.startAt = now;
            this.endAt = this.startAt.plusSeconds(60);
            return;
        }else if(startAt == null){
            startAt = now;
        }else if (endAt == null) {
            endAt = startAt.plusSeconds(60);
        }

        boolean isValid = startAt.isAfter(nowMinusSecond) && startAt.isBefore(endAt);

        if (isValid){
            this.startAt = startAt;
            this.endAt = endAt;
        }else{
            throw new IllegalArgumentException("Invalid dates");
        }
    }
}
