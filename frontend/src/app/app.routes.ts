import { Routes } from '@angular/router';
import { VotingSession } from './voting-session/voting-session';

export const routes: Routes = [
  {path: "voting", component: VotingSession},
  {path: "", redirectTo: "voting", pathMatch: "full"}
];
