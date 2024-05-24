

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

import { AdminloginService } from './adminlogin.service';
@Injectable({ 
  providedIn: 'root' 
}) 
// export const adminguardGuard: CanActivateFn = (route, state) => {
//   return true;
// };
export class adminguardGuard implements CanActivate {
  constructor(private adminloginService: AdminloginService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    console.log(this.adminloginService.isLoggedIn())
    console.log(this.adminloginService.isAuthenticated())
    if (this.adminloginService.isLoggedIn() && this.adminloginService.isAuthenticated()) { 
      const expectedRole = route.data['expectedRole'] as string;
      console.log("hi")
      if (expectedRole && this.adminloginService.hasRole(expectedRole)) { 
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
      this.router.navigate(['/AdminLogin']);
      return false;
    }
  }
}
