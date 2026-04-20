import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateSessionRequest } from '../model/request/create-session-request';
import { VotingSessionResponse } from '../model/response/voting-session-response';

@Injectable({
  providedIn: 'root',
})
export class VotingSessionService {

  private readonly API_URL = '/api/sessions'
  
  constructor(private http: HttpClient){}

  create(createSessionRequest: CreateSessionRequest): Observable<VotingSessionResponse>{
    return this.http.post<VotingSessionResponse>(`${this.API_URL}`, {...createSessionRequest});
  }

  findAll(): Observable<VotingSessionResponse[]>{
    return this.http.get<VotingSessionResponse[]>(`${this.API_URL}`)
  }

  findAllActivated(): Observable<VotingSessionResponse[]>{
    return this.http.get<VotingSessionResponse[]>(`${this.API_URL}/activated`)
  }

}
