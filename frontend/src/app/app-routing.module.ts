import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponentComponent} from "./components/home-component/home-component.component";
import {FeedingSessionFormComponent} from "./components/feeding-session-form/feeding-session-form.component";
import {LoginComponent} from "./components/login/login.component";
import {PhysicianHomeComponent} from "./components/physician-home/physician-home.component";
import {AdminHomeComponent} from "./components/admin-home/admin-home.component";
import {UpdateFeedingSessionComponent} from "./components/update-feeding-session/update-feeding-session.component";

const routes: Routes = [
  {path:"", component:HomeComponentComponent},
  {path:"login", component:LoginComponent},
  {path:"newFeedingSession", component:FeedingSessionFormComponent},
  {path:"physicianHome", component:PhysicianHomeComponent},
  {path:"adminHome", component:AdminHomeComponent},
  {path:"updateFeedingSession", component:UpdateFeedingSessionComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
