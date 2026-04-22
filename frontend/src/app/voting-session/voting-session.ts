import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { VotingSessionResponse } from './model/response/voting-session-response';
import { CreateSessionRequest } from './model/request/create-session-request';
import { VotingSessionService } from './service/voting-session.service';
import { VoteService } from '../vote/vote.service';

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
  cpfError: boolean = false;

  constructor(
    private votingSessionService: VotingSessionService,
    private voteService: VoteService
  ){}

  ngOnInit(){
    this.findAll();
  }

  createVotingSession(form: NgForm){
    if(form.invalid) return;

    const request: CreateSessionRequest = this.getDefaultVotingRequest();

    this.votingSessionService.create(request).subscribe({
      next: (value) => {
        this.votingSessions.push(value);
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  findAll(){
    this.votingSessionService.findAll().subscribe({
      next: (values) => {
        this.votingSessions = values;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  vote(voteValue: string, sessionId: number, form: NgForm){

    let cpf = (this.cpf ?? "").trim().replace(/\D/g, "");

    if (cpf.length !== 11) {
      this.cpfError = true;
      return;
    }

    this.cpfError = false;

    this.voteService.vote(voteValue, this.cpf, sessionId).subscribe({
      next: () => {
        this.votingSessions.forEach((v) => {
          if(v.id === sessionId){
            v.votingSessionInfo.totalVotes++

            if(voteValue === "YES"){
              v.votingSessionInfo.yesVotes++
            }
            else if(voteValue === "NO"){
              v.votingSessionInfo.noVotes++
            }
          }
        })
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

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

  dateFormat(date: Date): string{
    const localDate: Date = new Date(date);

    return localDate.toLocaleString(undefined, {
      hour12: false
    }).replace(",", "");
  
  }
}
