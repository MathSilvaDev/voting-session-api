package com.matheus.voting_session_api.member.entity;

import com.matheus.voting_session_api.vote.entity.Vote;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "member")
    private Set<Vote> votes = new HashSet<>();

    public Member(String cpf){
        this.cpf = normalizeCpf(cpf);
    }

    public void addVote(Vote vote){
        votes.add(vote);
    }

    public String normalizeCpf(String cpf){
        //TODO: Implement full CPF validation logic (currently only normalizing input)
        return cpf.replaceAll("[^0-9]", "");
    }

}
