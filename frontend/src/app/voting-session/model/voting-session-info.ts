import { VotingSessionResponse } from "./voting-session-response";

export interface VotingSessionInfo {
  votingSession: VotingSessionResponse;
  totalVotes: number;
  noVotes: number;
  yesVotes: number;
}