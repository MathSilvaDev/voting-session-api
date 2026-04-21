import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VoteRequest } from './model/vote-request';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class VoteService {
  
  private readonly API_URL = '/api/votes'

  constructor(private http: HttpClient){}


  //improve later | return values in backend
  vote(voteRequest: VoteRequest, cpf: string, sessionId: number): Observable<void>{
    return this.http.post<void>(
      `${this.API_URL}/${cpf}/sessions/${sessionId}`, {...voteRequest}
    )
  }
}
