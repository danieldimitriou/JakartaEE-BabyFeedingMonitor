import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FeedingSessionFormComponent} from "./components/feeding-session-form/feeding-session-form.component";
import {LoginComponent} from "./components/login/login.component";
import {PhysicianHomeComponent} from "./components/physician-home/physician-home.component";
import {AdminHomeComponent} from "./components/admin-home/admin-home.component";
import {UpdateFeedingSessionComponent} from "./components/update-feeding-session/update-feeding-session.component";
import {PhysicianAuthGuard} from "./helpers/physician-auth.guard";
import {AdminAuthGuard} from "./helpers/admin-auth.guard";

const routes: Routes = [
  {path:"", canActivate:[AdminAuthGuard], component:AdminHomeComponent},
  {path:"login", component:LoginComponent},
  {path:"newFeedingSession",canActivate:[AdminAuthGuard], component:FeedingSessionFormComponent},
  {path:"physicianHome",canActivate:[PhysicianAuthGuard], component:PhysicianHomeComponent},
  {path:"adminHome",canActivate:[AdminAuthGuard], component:AdminHomeComponent},
  {path:"updateFeedingSession",canActivate:[AdminAuthGuard], component:UpdateFeedingSessionComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
