import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateFeedingSessionComponent } from './update-feeding-session.component';

describe('UpdateFeedingSessionComponent', () => {
  let component: UpdateFeedingSessionComponent;
  let fixture: ComponentFixture<UpdateFeedingSessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateFeedingSessionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateFeedingSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
