package com.matheus.voting_session_api.exception.votingsession;

public class VotingSessionNotEnabledException extends RuntimeException{

    public VotingSessionNotEnabledException(){
        super("Voting session has expired or is not enabled");
    }

    public VotingSessionNotEnabledException(String message){
        super(message);
    }
}
