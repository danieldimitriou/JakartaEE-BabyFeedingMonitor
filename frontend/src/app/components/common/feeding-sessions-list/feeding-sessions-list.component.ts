import {Component, Sanitizer} from '@angular/core';
import {FeedingSession} from "../../../models/feeding-session";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../../services/api.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../services/authentication.service";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-feeding-sessions-list',
  templateUrl: './feeding-sessions-list.component.html',
  styleUrls: ['./feeding-sessions-list.component.scss']
})
export class FeedingSessionsListComponent {
  feedingSessions: FeedingSession[] = [];
  averageTime: number;
  averageAmountConsumed: number;
  isUserAdmin: boolean;
  error: string;
  isListEmpty: boolean;
  chartUrl
  filterByTimeForm: FormGroup;
  notAdmin: any;
  constructor(private apiService: ApiService, private router: Router,  private formBuilder: FormBuilder, private authService: AuthenticationService, private sanitizer: DomSanitizer
  ) {
    this.averageAmountConsumed = 0.0;
    this.averageTime = 0.0;
    if(this.authService.currentUserValue.role == "admin"){
     this.isUserAdmin = true;
    }
    this.getAllFeedingSessions();
  }
  ngOnInit() {
    this.filterByTimeForm = this.formBuilder.group({
      startDate: ['', Validators.required],
      endDate: ['', Validators.required]
    });
    // this.feedingSession = {
    //   amountConsumed: 0,
    //   date: new Date(),
    //   startTime: '',
    //   endTime: ''
    // };
  }
  get formValues() {
    return this.filterByTimeForm.controls;
  }
  onSubmit() {
    console.log(this.formValues["startDate"].value);
    console.log(this.formValues["endDate"].value);
  }

  getFeedingSessionChart(){
    this.apiService.getFeedingsessionChart().subscribe(
      next=> {
        console.log(next);
        this.chartUrl = 'data:image/png;base64,' + next["chart"];
      }
        // Assign base64 image data      }
    )
  }
  deleteFeedingSession(event: Event, id: number) {
    event.stopPropagation();
    this.apiService.deleteFeedingSession(id).subscribe(
      () => {
        const index = this.feedingSessions.findIndex(session => session.id === id);
        if (index !== -1) {
          this.feedingSessions.splice(index, 1); // Remove the deleted session from the array
          let amountConsumedSum = 0;
          let durationSum = 0;
          for(let feedingSession of this.feedingSessions){
            amountConsumedSum += feedingSession.amountConsumed;
            durationSum += feedingSession.duration;
          }
          this.averageTime = durationSum / this.feedingSessions.length;
          this.averageAmountConsumed = amountConsumedSum / this.feedingSessions.length;
        }
      }
    );
  }
  sortFeedingSessionsByDate() {
    this.feedingSessions.sort((a, b) => {
      const dateA = new Date(a.date);
      const dateB = new Date(b.date);
      return dateA.getTime() - dateB.getTime();
    });
  }
  filterResults() {
    let startDate = this.formValues["startDate"].value;
    let endDate = this.formValues["endDate"].value;
    this.apiService.getAllByDates(startDate, endDate).subscribe(
      next => {
        this.feedingSessions = [];
        if(next["feedingSessionList"].length  >= 1){
          this.isListEmpty = false;
          for(let session of next["feedingSessionList"] ){
            this.feedingSessions.push(session);
          }
          this.averageTime = next["averageTime"];
          this.averageAmountConsumed = next["averageAmountConsumed"];
        }
        else{
          this.isListEmpty = true;
          this.error = "No feeding sessions available at those dates";
        }

        // console.log(next);
      }
    )
  }

  getAllFeedingSessions(){
    this.apiService.getAllFeedingSessions().subscribe(
      next => {
        console.log(next);
        if(next["feedingSessionList"].length  >= 1) {
          this.isListEmpty = false;
          this.averageTime = next["averageTime"];
          this.averageAmountConsumed = next["averageAmountConsumed"];
          // @ts-ignore
          for (let session of next["feedingSessionList"]) {
            this.feedingSessions.push(session);
          }
          this.sortFeedingSessionsByDate(); // Sort the feeding sessions by date
          console.log(this.feedingSessions);
        }else{
          this.isListEmpty = true;
          this.error = "No feeding sessions available."
        }
      }
    )
  }

  clearFilter(){
    this.getAllFeedingSessions();

  }
}
