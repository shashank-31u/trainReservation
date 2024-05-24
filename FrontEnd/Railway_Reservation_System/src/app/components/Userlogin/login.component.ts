import { Component, OnInit } from '@angular/core';
import { error, log } from 'console';

import { LoginService } from '../../services/Userlogin.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  credentials={
    username:"",
    password:""
  }

  constructor(private loginService:LoginService,private router: Router){

  }
  
  ngOnInit(): void {
    
  }
  onSubmit(){
    console.log("form is submitted");
    if((this.credentials.username !="" && this.credentials.password !="") && (this.credentials.username!=null && this.credentials.password!=null))
    {
      console.log("we have to submit the form");
        // token generate
      this.loginService.generateToken(this.credentials).subscribe(
        (Response:any)=>{
          console.log(Response.token);
          this.loginService.loginUser("token")

          // use router here
          this.router.navigate(['/Dashboard']);
          // window.location.href="/Dashboard"
        },
        errors=>{
          console.log(errors);
        }
      )

    }else{
        console.log("fields are empty 1");
    }
  }

}


