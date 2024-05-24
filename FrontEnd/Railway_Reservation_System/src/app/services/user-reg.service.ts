import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserRegService {

  url="http://localhost:8081"
  constructor(private http:HttpClient) { }

  public addUser(user:any){
    return this.http.post(`${this.url}/user/registeruser`,user);
  }
}
