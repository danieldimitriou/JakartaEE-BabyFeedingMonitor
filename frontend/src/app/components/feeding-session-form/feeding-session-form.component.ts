import { Component, OnInit } from '@angular/core';
import { FeedingSession } from "../../models/feeding-session";
import { ApiService } from "../../services/api.service";
import { DatePipe } from "@angular/common";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-feeding-session-form',
  templateUrl: './feeding-session-form.component.html',
  styleUrls: ['./feeding-session-form.component.scss'],
  providers: [DatePipe],
})
export class FeedingSessionFormComponent implements OnInit {


  feedingSessionForm: FormGroup;
  feedingSession: FeedingSession;
  constructor(
    private router: Router,
    private apiService: ApiService,
    private datePipe: DatePipe,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit() {
    this.feedingSessionForm = this.formBuilder.group({
      amountConsumed: ['', Validators.required],
      date: ['', Validators.required],
      startTime: ['', Validators.required],
      endTime: ['', Validators.required]
    });
    this.feedingSession = {
      amountConsumed: 0,
      date: new Date(),
      startTime: '',
      endTime: ''
    };
  }
  get formValues() {
    return this.feedingSessionForm.controls;
  }
  onSubmit() {
    if (this.feedingSessionForm.invalid) {
      return;
    }
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
      amountConsumed: this.formValues['amountConsumed'].value,
      date: this.formValues['date'].value,
      startTime: startTime,
      endTime: endTime,
    };
    console.log(this.feedingSession);
    this.apiService.createFeedingSession(this.feedingSession).subscribe(
      next => {
        if(next["message"] == "Resource Created successfully"){
          this.router.navigate(['/adminHome']);
        }
        console.log(next["message"]);
      }
    );
  }
}
