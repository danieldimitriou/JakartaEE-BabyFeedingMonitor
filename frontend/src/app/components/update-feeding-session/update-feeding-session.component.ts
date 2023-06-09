import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {FeedingSession} from "../../models/feeding-session";
import {ApiService} from "../../services/api.service";
import {DatePipe} from "@angular/common";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-feeding-session',
  templateUrl: './update-feeding-session.component.html',
  styleUrls: ['./update-feeding-session.component.scss'],
  providers: [DatePipe],
})
export class UpdateFeedingSessionComponent {
  feedingSessionId: number;


  feedingSessionForm: FormGroup;
  feedingSession: FeedingSession;
  constructor(
    private apiService: ApiService,
    private datePipe: DatePipe,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.feedingSessionId = params['id'];
      // You can now use the 'id' parameter in your component logic
      console.log(this.feedingSessionId);
    });
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
      console.log(next);
    });
  }
}
