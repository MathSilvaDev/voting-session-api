import { VotingSessionInfo } from "./voting-session-info";

export interface VotingSessionResponse {
  id: number;
  topic: string;
  description: string;
  startAt: Date;
  endAt: Date;
  isActivated: boolean
  votingSessionInfo: VotingSessionInfo;
}
