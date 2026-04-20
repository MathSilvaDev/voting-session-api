import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { VotingSessionResponse } from './model/response/voting-session-response';
import { CreateSessionRequest } from './model/request/create-session-request';
import { VotingSessionService } from './service/voting-session.service';

@Component({
  selector: 'app-voting-session',
  imports: [ FormsModule, CommonModule],
  templateUrl: './voting-session.html',
  styleUrl: './voting-session.scss',
})
export class VotingSession {

  topic: string = '';
  description: string = '';
  startAt: string | null = null;
  endAt: string | null = null;

  votingSessions: VotingSessionResponse[] = [];

  cpf: string = '';

  constructor(private votingSessionService: VotingSessionService){}

  createVotingSession(){
    const request: CreateSessionRequest = this.getDefaultVotingRequest();

    this.votingSessionService.create(request).subscribe({
      next: (value) => {
        this.votingSessions.push(value);
      },
      error: (err) => {
        console.log(request);
      }
    });
  }

  vote(){}

  getDefaultVotingRequest(): CreateSessionRequest{
    const topic: string = this.topic.trim();
    const description: string = this.description.trim();

    let startAt: string | null = null;
    let endAt: string | null = null;
    
    if(this.startAt !== null){
      startAt = new Date(this.startAt).toISOString();
    }
    if(this.endAt !== null){
      endAt = new Date(this.endAt).toISOString();
    }

    return {
      "topic": topic,
      "description": description,
      "startAt": startAt,
      "endAt": endAt
    }
  }
}
