import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../../services/authentication.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit{
  isLoggedIn: boolean;
  isUserAdmin: boolean;

  constructor(private authService: AuthenticationService) {
  }
  ngOnInit() {
    if(this.authService.currentUserValue){
      // console.log(this.authService.currentUserValue["role"])
      this.isLoggedIn = true;
      if(this.authService.currentUserValue["role"] == "ADMIN"){
        this.isUserAdmin = true;
      }
    }

  }

  logout() {
    this.authService.logout();
  }
}
