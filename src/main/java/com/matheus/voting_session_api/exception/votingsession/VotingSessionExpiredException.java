package com.matheus.voting_session_api.exception.votingsession;

public class VotingSessionExpiredException extends RuntimeException{

    public VotingSessionExpiredException(){
        super("Voting session has expired");
    }

    public VotingSessionExpiredException(String message){
        super(message);
    }
}
