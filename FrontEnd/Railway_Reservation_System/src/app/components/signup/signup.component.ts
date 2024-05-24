import { Component, OnInit } from '@angular/core';
import { UserRegService } from '../../services/user-reg.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit{
  
  constructor(private userregService:UserRegService){}
  public user={
    userId:'',
    password:'',
    fullName:'',
    gender:'',
    email:'',
    phoneNumber:'',
    age:''

  }
  ngOnInit(): void {}

  formSubmit(){
    console.log(this.user);
    if(this.user.userId=='' || this.user.userId==null)
    {
      alert("userId is required!!")
      return;
    }

    this.userregService.addUser(this.user).subscribe(

      (data)=>{
        console.log(data);
        window.location.href="/Login"
        alert('success');
      },
      (error) =>{
        console.log(error);
        alert('something went wrong');
      }
   
    )

  }
  

}
