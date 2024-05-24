import { Component, OnInit } from '@angular/core';
import { AdminloginService } from '../../services/adminlogin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrl: './admin-login.component.css'
})
export class AdminLoginComponent  implements OnInit{

  credentials={
    username:"",
    password:""
  }

 
 constructor(private adminloginService:AdminloginService,private router: Router){

 }
 ngOnInit(): void {

 }
 onSubmit(){
  // console.log("form is submitted");
  if((this.credentials.username !="" && this.credentials.password !="") && (this.credentials.username!=null && this.credentials.password!=null))
  {
    console.log("we have to submit the form");
      // token generate
    this.adminloginService.generateToken(this.credentials).subscribe(
      (Response:any)=>{
        console.log("method works");
        // console.log(Response.token);
        this.adminloginService.loginAdmin(Response.token);
        console.log("works")

        this.router.navigate(['/AdminDashboard']);
        // window.location.href="/AdminDashboard"
      },
      error=>{
        console.log("error!")
        console.log(error);
      }
    )

  }else{
      console.log("fields are empty");
  }
}


}
