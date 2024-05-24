import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private userRole: string | null = null;
  private isAuthenticatedValue: boolean = false;

  url = "http://localhost:8081";

  constructor(private http: HttpClient) {}

  // calling server to generate token
  public generateToken(credentials: any) {
    // token generate
    return this.http.post(`${this.url}/user/authenticateuser`, credentials);
  }

  // public loginUser(token: string) {
  //   localStorage.setItem('token', token);
  //   this.isAuthenticatedValue = true;
  //   this.userRole = 'user';
  // }
  public loginUser(token: string) {
    this.isAuthenticatedValue = true;
    console.log(this.isAuthenticatedValue)
    this.userRole = 'user';
    localStorage.setItem('token', token);
    return true;
  }

  public isLoggedIn() {
    let tokenStr = localStorage.getItem('token');
    return tokenStr !== undefined && tokenStr !== "" && tokenStr !== null;
  }

  public logout() {
    console.log('logout')
    localStorage.removeItem('token');
    this.isAuthenticatedValue = false;
    this.userRole = null;
    
  }

  public getToken() {
    return localStorage.getItem('token');
  }

  public isAuthenticated(): boolean {
    return this.isAuthenticatedValue;
  }

  public hasRole(expectedRole: string): boolean {
    return this.userRole === expectedRole;
  }
}
