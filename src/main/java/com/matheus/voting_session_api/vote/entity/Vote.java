package com.matheus.voting_session_api.vote.entity;

import com.matheus.voting_session_api.member.entity.Member;
import com.matheus.voting_session_api.vote.enums.VoteValue;
import com.matheus.voting_session_api.votingsession.entity.VotingSession;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vote")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoteValue voteValue;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "voting_session_id", nullable = false)
    private VotingSession votingSession;

    public Vote(VoteValue voteValue, Member member, VotingSession votingSession){
        this.voteValue = voteValue;
        this.member = member;
        this.votingSession = votingSession;
    }
}
