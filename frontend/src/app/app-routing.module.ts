import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponentComponent} from "./components/home-component/home-component.component";
import {FeedingSessionFormComponent} from "./components/feeding-session-form/feeding-session-form.component";
import {LoginComponent} from "./components/login/login.component";

const routes: Routes = [
  {path:"", component:HomeComponentComponent},
  {path:"login", component:LoginComponent},
  {path:"newFeedingSession", component:FeedingSessionFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
