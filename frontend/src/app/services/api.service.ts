import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {FeedingSession} from "../models/feeding-session";



// const httpOptions = {
//   headers: new HttpHeaders({
//     'Content-Type': 'multipart/form-data'
//   })
// };
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private api = "http://localhost:8080/backend-1.0-SNAPSHOT/api";

  constructor(private http: HttpClient, private router: Router) { }

  createFeedingSession(newFeedingSession: FeedingSession) {
    let headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post("http://localhost:8080/backend_war_exploded/api/feedingSession/create", newFeedingSession, { headers: headers });
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
}
