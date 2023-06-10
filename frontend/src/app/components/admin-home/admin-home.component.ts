import {Component, OnInit} from '@angular/core';
import {FeedingSession} from "../../models/feeding-session";
import {ApiService} from "../../services/api.service";
import {DatePipe} from "@angular/common";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss'],
  providers: [DatePipe],
})
export class AdminHomeComponent implements OnInit{
  feedingSessions: FeedingSession[] = [];
  averageTime: number = 0;
  averageAmountConsumed: number = 0;

  filterByTimeForm: FormGroup;

  constructor(private apiService: ApiService, private router: Router,  private formBuilder: FormBuilder
  ) {
    this.apiService.getAllFeedingSessions().subscribe(
      next => {
        console.log(next);
        this.averageTime = next["averageTime"];
        this.averageAmountConsumed = next["averageAmountConsumed"];
        // @ts-ignore
        for(let session of next["feedingSessionList"] ){
          this.feedingSessions.push(session);
        }
        console.log(this.feedingSessions);
      }
    )
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

  deleteFeedingSession(id) {
    this.apiService.deleteFeedingSession(id).subscribe(
      () => {
        const index = this.feedingSessions.findIndex(session => session.id === id);
        if (index !== -1) {
          this.feedingSessions.splice(index, 1); // Remove the deleted session from the array
        }
      }
    );
  }
  get formValues() {
    return this.filterByTimeForm.controls;
  }
  onSubmit() {
    console.log(this.formValues["startDate"].value);
    console.log(this.formValues["endDate"].value);
  }

  filterResults() {
    let startDate = this.formValues["startDate"].value;
    let endDate = this.formValues["endDate"].value;
    this.apiService.getAllByDates(startDate, endDate).subscribe(
      next => {
        this.feedingSessions = [];
        for(let session of next["feedingSessionList"] ){
          this.feedingSessions.push(session);
        }
        console.log(next);
      }
    )
  }
}
