import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-voting-session',
  imports: [ FormsModule, CommonModule],
  templateUrl: './voting-session.html',
  styleUrl: './voting-session.scss',
})
export class VotingSession {

  topic: string = '';
  description: string = '';
  startAt: string = '';
  endAt: string = '';

  createVotingSession(){}
}
