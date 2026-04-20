export interface CreateSessionRequest {
  topic: string;
  description: string;
  startAt: string | null;
  endAt: string | null;
}
