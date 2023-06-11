import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedingSessionsListComponent } from './feeding-sessions-list.component';

describe('FeedingSessionsListComponent', () => {
  let component: FeedingSessionsListComponent;
  let fixture: ComponentFixture<FeedingSessionsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedingSessionsListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedingSessionsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
