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
    })
    return this.http.post(this.api + `/feedingSession/create`, newFeedingSession, {headers : headers});
  }
}
