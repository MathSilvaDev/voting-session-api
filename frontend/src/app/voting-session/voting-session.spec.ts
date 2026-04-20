import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VotingSession } from './voting-session';

describe('VotingSession', () => {
  let component: VotingSession;
  let fixture: ComponentFixture<VotingSession>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VotingSession]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VotingSession);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
