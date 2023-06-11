// import {Injectable} from '@angular/core';
// import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
// import {Observable} from 'rxjs';
// import {AuthenticationService} from "../services/authentication.service";
//
//
// @Injectable()
// export class BasicAuthInterceptor implements HttpInterceptor {
//   constructor(private authenticationService: AuthenticationService) {
//   }
//
//   intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
//     // add authorization header with basic auth credentials if available
//     const currentUser = this.authenticationService.currentUserValue;
//     if (currentUser && currentUser.jwt) {
//       request = request.clone({
//         setHeaders: {
//           'x-access-token':`${currentUser.jwt}`
//         }
//       });
//     }
//
//     return next.handle(request);
//   }
// }
