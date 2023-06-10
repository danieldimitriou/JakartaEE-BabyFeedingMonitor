import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../services/api.service";
import {FeedingSession} from "../../models/feeding-session";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-physician-home',
  templateUrl: './physician-home.component.html',
  styleUrls: ['./physician-home.component.scss']
})
export class PhysicianHomeComponent implements OnInit{
  feedingSessions: FeedingSession[] = [];
  averageTime: number;
  averageAmountConsumed: number;
  filterByTimeForm: FormGroup;


  constructor(private apiService: ApiService, private formBuilder: FormBuilder) {
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
  }

  get formValues() {
    return this.filterByTimeForm.controls;
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
  getAllFeedingSessions(){

  }
}
