import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LoginData} from "../models/login-data";
import {BehaviorSubject, catchError, map, Observable, throwError} from "rxjs";
import {UserData} from "../models/user-data.model";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private loginUrl = "http://localhost:8080/backend_war_exploded/api/user/login";
  readonly currentUserSubject: BehaviorSubject<UserData>;
  private currentUser: Observable<UserData>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<UserData>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): UserData {
    return this.currentUserSubject.value;
  }

  logout(){
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
  login(loginData: LoginData): Observable<any>{
    let headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.post(this.loginUrl, loginData, {headers: headers}).pipe(
      map(res => {
        if (res["statusCode"] == 200) {
          console.log(res);
          this.saveUserToLocalStorage(res["jwt"], res["role"]);
          return res["statusCode"];
        } else {
          return res["status"];
        }
      }), catchError(e => {
        return throwError(e);
      })
    );


  }
  saveUserToLocalStorage(jwt: string, role: string){
    let user: UserData = {
      jwt: jwt,
      role: role
    }
    localStorage.setItem('currentUser', JSON.stringify({jwt: user.jwt, role: user.role}));
    this.currentUserSubject.next(user);
    return user;
  }
}
