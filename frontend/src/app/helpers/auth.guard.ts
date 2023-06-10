import { Injectable } from '@angular/core';
import {Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {AuthenticationService} from "../services/authentication.service";


@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.authenticationService.currentUserValue;
    // console.log(currentUser);
    if (currentUser.role == "admin") {
      // logged in so return true
      return true;
    }
    // console.log(this.route.snapshot.url)

    // not logged in so redirect to login page with the return url
    // console.log(state.url)
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    // console.log(state.url);
    return false;
  }
}
