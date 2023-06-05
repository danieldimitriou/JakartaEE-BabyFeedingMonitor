import { Component } from '@angular/core';
import {FeedingSession} from "../../models/feeding-session";
import {ApiService} from "../../services/api.service";

@Component({
  selector: 'app-feeding-session-form',
  templateUrl: './feeding-session-form.component.html',
  styleUrls: ['./feeding-session-form.component.scss']
})
export class FeedingSessionFormComponent {

  constructor(private apiService: ApiService) {
    }

  feedingSession: FeedingSession = {
    // @ts-ignore
    amountConsumed: null,
    // @ts-ignore
    startDate: null,
    // @ts-ignore
    endDate: null
  };

  onSubmit() {
    // Convert the date and time strings to LocalDateTime format if needed
    const startDate = new Date(this.feedingSession.startDate);
    const endDate = new Date(this.feedingSession.endDate);
    this.feedingSession.startDate = startDate.toISOString();
    this.feedingSession.endDate = endDate.toISOString();

    // Perform further processing or API call with the feedingSession object
    console.log(this.feedingSession);
    this.apiService.createFeedingSession(this.feedingSession).subscribe(
      next=>{
        console.log(next)
      }
    );
  }
}
