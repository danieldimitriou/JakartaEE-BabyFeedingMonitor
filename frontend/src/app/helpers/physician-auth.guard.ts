import { Injectable } from '@angular/core';
import {Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {AuthenticationService} from "../services/authentication.service";


@Injectable({ providedIn: 'root' })
export class PhysicianAuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.authenticationService.currentUserValue;
    if(currentUser.role == "ADMIN"){
      this.router.navigate(['/adminHome']);
    }
    if(!currentUser){
      this.router.navigate(['/login']);
      return false;
    }
    if (currentUser.role == "PHYSICIAN") {
      // logged in so return true
      return true;
    }
    return false;
  }
}
