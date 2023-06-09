import { Component } from '@angular/core';
import {ApiService} from "../../services/api.service";
import {FeedingSession} from "../../models/feeding-session";

@Component({
  selector: 'app-physician-home',
  templateUrl: './physician-home.component.html',
  styleUrls: ['./physician-home.component.scss']
})
export class PhysicianHomeComponent {
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


  getAllFeedingSessions(){

  }
}
