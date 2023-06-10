import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Router} from "@angular/router";
import {FeedingSession} from "../models/feeding-session";
import {AuthenticationService} from "./authentication.service";



// const httpOptions = {
//   headers: new HttpHeaders({
//     'Content-Type': 'multipart/form-data'
//   })
// };
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient, private router: Router, private authService: AuthenticationService) { }

  createFeedingSession(newFeedingSession: FeedingSession) {
    const token = this.authService.currentUserValue["jwt"];
    console.log(token);
    let headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      // 'Authorization': `Bearer ${token}`
    });
    return this.http.post("http://localhost:8080/backend_war_exploded/api/feedingSession/create", newFeedingSession, { headers: headers });
  }

  getFeedingSessionById(id: number){
    let headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      // 'Authorization': `Bearer ${token}`
    });
    return this.http.get(`http://localhost:8080/backend_war_exploded/api/feedingSession/${id}`, { headers: headers });
  }
  updateFeedingSession(newFeedingSession: FeedingSession){
    let headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post("http://localhost:8080/backend_war_exploded/api/feedingSession/update", newFeedingSession, { headers: headers });
  }
  getAllFeedingSessions() {
    let headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get("http://localhost:8080/backend_war_exploded/api/feedingSession/getAll", { headers: headers });
  }
  deleteFeedingSession(id: number) {
    let headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get(`http://localhost:8080/backend_war_exploded/api/feedingSession/delete/${id}`, { headers: headers });
  }

  getFeedingsessionChart(){
    let headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get(`http://localhost:8080/backend_war_exploded/api/feedingSession/chart`, { headers: headers });
  }

  getAllByDates(startDate: Date, endDate: Date) {
    let headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    //
    // let startDateString: string = startDate instanceof Date ? startDate.toISOString().split('T')[0] : '';
    // let endDateString: string = endDate instanceof Date ? endDate.toISOString().split('T')[0] : '';

    // Create an instance of HttpParams and set the query parameters
    let params: HttpParams = new HttpParams()
      .set('startDate', startDate.toString())
      .set('endDate', endDate.toString());

    console.log(startDate)
    console.log(endDate)
    return this.http.get('http://localhost:8080/backend_war_exploded/api/feedingSession/filterByDates?', {
      headers: headers,
      params: params,
    });
  }
}
