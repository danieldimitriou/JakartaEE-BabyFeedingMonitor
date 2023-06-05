import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedingSessionFormComponent } from './feeding-session-form.component';

describe('FeedingSessionFormComponent', () => {
  let component: FeedingSessionFormComponent;
  let fixture: ComponentFixture<FeedingSessionFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedingSessionFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedingSessionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
