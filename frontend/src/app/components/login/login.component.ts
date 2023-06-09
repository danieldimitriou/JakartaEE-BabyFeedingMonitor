import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../../services/api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FeedingSession} from "../../models/feeding-session";
import {AuthenticationService} from "../../services/authentication.service";
import {LoginData} from "../../models/login-data";
import {HttpResponse} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
//@ts-ignore
  loginForm: FormGroup;
  loginData: LoginData;
  loading = false;
  submitted = false;
  returnUrl: string | undefined;
  error = '';

  constructor(private authService: AuthenticationService, private formBuilder: FormBuilder, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
    this.loginData = {
      email: "",
      password: ""
    }
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/home';
    // console.log(this.returnUrl)
  }
  get formValues() {
    return this.loginForm.controls;
  }

  onSubmit() {
    this.loginData = {
      email: this.formValues["email"].value,
      password: this.formValues["password"].value
    }
    this.authService.login(this.loginData).subscribe(
      next=> {
        console.log(next);
        console.log(next.statusCode)
        if(next["statusCode"] === 200){
          let jwt = next["jwt"];
          console.log(jwt);
          console.log(this.authService.currentUserSubject);
          console.log(this.authService.currentUserValue);

          this.router.navigate(['/adminHome']); // Redirect to adminHome page
          }
      }
    )
  }
}
