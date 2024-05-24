import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminloginService {
  private adminRole: string | null = null;
  private isAuthenticatedValue: boolean = false;
  url="http://localhost:8082"

  constructor(private http: HttpClient) {}
  // calling server to generate token

  generateToken(credentials:any){
    // token generate
    return this.http.post(`${this.url}/admin/authenticate`,credentials);
  }
  
  public loginAdmin(token: string){
    this.isAuthenticatedValue = true;
    console.log(this.isAuthenticatedValue)
    this.adminRole = 'admin';
    localStorage.setItem('token', token)
    return true;
  }
  public isLoggedIn() {
    let tokenStr = localStorage.getItem('token');
    return tokenStr !== undefined && tokenStr !== "" && tokenStr !== null;
  }

  

  public logout(){
    localStorage.removeItem('token')
    return true;
  }

  public getToken(){
    return localStorage.getItem("token");
  }

  public isAuthenticated(): boolean {
    return this.isAuthenticatedValue;
  }

  public hasRole(expectedRole: string): boolean {
    return this.adminRole === expectedRole;
  }
}
