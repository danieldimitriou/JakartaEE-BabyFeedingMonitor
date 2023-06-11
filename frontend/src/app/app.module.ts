import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponentComponent } from './components/home-component/home-component.component';
import { FeedingSessionFormComponent } from './components/feeding-session-form/feeding-session-form.component';
import { LoginComponent } from './components/login/login.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgxMaterialTimepickerModule} from "ngx-material-timepicker";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { TimePickerComponent } from './components/time-picker/time-picker.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { PhysicianHomeComponent } from './components/physician-home/physician-home.component';
import { UpdateFeedingSessionComponent } from './components/update-feeding-session/update-feeding-session.component';
import { HeaderComponent } from './components/common/header/header.component';
import { FeedingSessionsListComponent } from './components/common/feeding-sessions-list/feeding-sessions-list.component';
// import {BasicAuthInterceptor} from "./helpers/auth.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponentComponent,
    FeedingSessionFormComponent,
    LoginComponent,
    TimePickerComponent,
    AdminHomeComponent,
    PhysicianHomeComponent,
    UpdateFeedingSessionComponent,
    HeaderComponent,
    FeedingSessionsListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgxMaterialTimepickerModule,
    BrowserAnimationsModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent],

})
export class AppModule { }
