import { Component } from '@angular/core';
import {FeedingSession} from "../../models/feeding-session";
import {ApiService} from "../../services/api.service";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss'],
  providers: [DatePipe],
})
export class AdminHomeComponent {
  feedingSessions: FeedingSession[] = [];
  constructor(private apiService: ApiService) {
    this.apiService.getAllFeedingSessions().subscribe(
      next => {
        console.log(next);
        // @ts-ignore
        for(let session of next ){
          this.feedingSessions.push(session);
        }
        console.log(this.feedingSessions);
      }
    )
  }

  deleteFeedingSession(id){
    this.apiService.deleteFeedingSession(id).subscribe(
      next => {
        console.log(next);
      }
    )

  }

  createFeedingSession(){}
}
