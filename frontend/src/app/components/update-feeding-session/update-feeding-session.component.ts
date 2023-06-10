import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {FeedingSession} from "../../models/feeding-session";
import {ApiService} from "../../services/api.service";
import {DatePipe} from "@angular/common";
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-update-feeding-session',
  templateUrl: './update-feeding-session.component.html',
  styleUrls: ['./update-feeding-session.component.scss'],
  providers: [DatePipe],
})
export class UpdateFeedingSessionComponent {
  feedingSessionId: number;
  feedingSessionData: FeedingSession;
  feedingSessionForm: FormGroup;
  feedingSession: FeedingSession;

  constructor(
    private router: Router,
    private apiService: ApiService,
    private datePipe: DatePipe,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ) {
    this.feedingSession = {
      amountConsumed: 0,
      date: new Date(),
      startTime: '',
      endTime: ''
    };
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.feedingSessionId = params['id'];
      // You can now use the 'id' parameter in your component logic
      this.apiService.getFeedingSessionById(this.feedingSessionId).subscribe(
        next =>{
          console.log(next);
          console.log(next["amountConsumed"]);
          this.feedingSessionData = {
            amountConsumed: next["amountConsumed"],
            startTime: next["startTime"],
            endTime: next["endTime"],
            date: next["date"],
            duration: next["duration"]
          }
        }
      )
    });
    this.feedingSessionForm = this.formBuilder.group({
      amountConsumed: ['', Validators.required],
      date: ['', Validators.required],
      startTime: ['', Validators.required],
      endTime: ['', Validators.required]
    });

  }
  formatTime(time: string): string {
    const [hours, minutes] = time.split(':');
    return `${hours}:${minutes}`;
  }
  get formValues() {
    return this.feedingSessionForm.controls;
  }
  onSubmit(){
    if (this.feedingSessionForm.invalid) {
      return;
    }


    console.log(this.feedingSession);

    const startDateTime = new Date();
    const startTimeParts = this.feedingSessionForm.value.startTime.split(':');
    startDateTime.setHours(Number(startTimeParts[0]), Number(startTimeParts[1]), 0, 0);

    const endDateTime = new Date();
    const endTimeParts = this.feedingSessionForm.value.endTime.split(':');
    endDateTime.setHours(Number(endTimeParts[0]), Number(endTimeParts[1]), 0, 0);

    const startTime = this.datePipe.transform(startDateTime, 'HH:mm:ss');
    const endTime = this.datePipe.transform(endDateTime, 'HH:mm:ss');
    console.log(startTime);

    this.feedingSession = {
      id: this.feedingSessionId,
      amountConsumed: this.formValues['amountConsumed'].value,
      date: this.formValues['date'].value,
      startTime: startTime,
      endTime: endTime,
    };

    console.log(this.feedingSession);

    this.apiService.updateFeedingSession(this.feedingSession).subscribe((next) => {
      if(next["duration"]){
        this.router.navigate(['/adminHome'])
      }
      console.log(next);
    });
  }

}
