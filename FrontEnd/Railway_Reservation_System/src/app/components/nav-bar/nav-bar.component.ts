import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/Userlogin.service';
import { AdminloginService } from '../../services/adminlogin.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent implements OnInit{
  public userloggedIn=false;
  public adminloggedIn=false;
  
  constructor(private loginService:LoginService, private adminloginService:AdminloginService){

  }
  ngOnInit(): void {
    this.userloggedIn=this.loginService.isLoggedIn()
    this.adminloggedIn=this.adminloginService.isLoggedIn()
  }

  

  logoutUser(){
    this.loginService.logout()
    location.reload()
  }
  logoutAdmin(){
    this.adminloginService.logout()
    location.reload()
  }
}
