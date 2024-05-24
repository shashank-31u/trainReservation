import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
declare var Razorpay : any;
@Component({
  selector: 'app-razorpay',
  templateUrl: './razorpay.component.html',
  styleUrl: './razorpay.component.css'
})
export class RazorpayComponent {
  payNow(){
    const RazorpayOptions={
      description:'Razorpay Payment',
      currency:'INR',
      amount:400000,
      name:'Aniket',
      key:'rzp_test_5lVeUBlx0k6yOl',
      image:'https://i.imgur.com/FApqk3D.jpeg',
      prefill: {
        name:'Aniket',
        email:'asnarale7@gmail.com',
        phone:'7666438717',
      },
      theme:{
        color:'#37254'
      },
      modal:{
        ondismiss: () =>{
          console.log("dismissed")
        }
      }
    }
    const successCallback=(paymentid: any) => {
      console.log(paymentid)
    }
    const failureCallback=(e: any) => {
      console.log(e)
    }

    Razorpay.open(RazorpayOptions,successCallback,failureCallback)
  }
}
