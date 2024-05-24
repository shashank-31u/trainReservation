

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { LoginService } from './Userlogin.service';
@Injectable({ 
  providedIn: 'root' 
}) 
export class AuthGuard implements CanActivate {
  constructor(private loginService: LoginService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    console.log(this.loginService.isLoggedIn())
    console.log(this.loginService.isAuthenticated())
    if (this.loginService.isLoggedIn() && this.loginService.isAuthenticated()) { 
      const expectedRole = route.data['expectedRole'] as string;
      console.log("hi")
      if (expectedRole && this.loginService.hasRole(expectedRole)) { 
        // User is authenticated and has the expected role, allow access
        return true;
      } else { 
        // User doesn't have the expected role, redirect to unauthorized page or handle as needed
        console.error(`Unauthorized access. Expected role: ${expectedRole}`);
        this.router.navigate(['/Unauthorized']); // Adjust this line based on your application's needs
        return false;
      }
    } else { 
      // User is not authenticated, redirect to login page
      this.router.navigate(['/UserLogin']);
      return false;
    }
  }
}

// @Injectable({
//   providedIn: 'root'
// })
// export class AuthGuard implements CanActivate {

//   constructor(private loginService: LoginService, private router: Router) {}

//   canActivate(
//     next: ActivatedRouteSnapshot,
//     state: RouterStateSnapshot): boolean {
//     const expectedRole = next.data['expectedRole'];

//     // if ((this.loginService.isLoggedIn())&&!this.loginService.isAuthenticated() || !this.loginService.hasRole(expectedRole)) {
//     //   this.router.navigate(['/Login']); // Redirect to login if not authenticated or lacks role
//     //   return false;
//     // }
//  ---------------------------------
//     if (!this.loginService.isLoggedIn() || !this.loginService.isAuthenticated() || !this.loginService.hasRole(expectedRole)) { 
//       this.router.navigate(['/UserLogin']); 
//     // Redirect to login if not authenticated or lacks role 
//       return false; 
//     }

//     return true;
//   } 


// --------------------------------------

  // previous


  // constructor(private loginService: LoginService, private router: Router) { }
  // canActivate(
  //   route: ActivatedRouteSnapshot,
  //   state: RouterStateSnapshot): boolean {
  //   if (this.loginService.isLoggedIn()) {       // User is authenticated, allow access      
  //     return true;
  //   }
  //   else{       // User is not authenticated, redirect to login page      
  //     this.router.navigate(['/UserLogin']);
  //     return false;
  //   }
  // } 
    




// }



// import { Injectable } from '@angular/core';
// import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
// import { Observable } from 'rxjs';
// import { LoginService } from './Userlogin.service';
// import { Router } from '@angular/router';

// @Injectable({
//   providedIn: 'root',
// })
// export class AuthGuard implements CanActivate {

//   constructor(private loginService:LoginService, private router:Router){

//   }

//   canActivate(
//     next: ActivatedRouteSnapshot,
//     state: RouterStateSnapshot
//   ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
//     // Your authentication logic goes here
//     if(this.loginService.isLoggedIn()){
//       return true;

//     }
    

//     this.router.navigate(['Login'])


//     return false; // or false based on authentication status
    
//   }
// }
