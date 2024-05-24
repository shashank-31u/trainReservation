import { Component, Input, OnInit } from '@angular/core';


export interface BookingDetails{
  bookingId:'',
  pnr:'',
  bookingStatus:'',
  sourceCity:'',
  destinationCity:'',
  trainName:'',
  trainNumber:'',
  dateOfJourney:'',
  passengerOutboundDTOList: [{ passengerName: '',passengerAge: '',gender:'',bookingStatus:'',coachNumber:'',coachType:'',seatNumber:'',seatType:''}]

}  
export interface passengerOutboundDTOList{
  passengerName: '',
  passengerAge: '',
  gender:'',
  bookingStatus:'',
  coachNumber:'',
  coachType:'',
  seatNumber:'',
  seatType:''
}


@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrl: './booking.component.css'
})
export class BookingComponent implements OnInit{
  @Input() item = '';
  // @Input() dataSource:any
  ticketDatainfo:any
  Booking={
    bookingId:'',
    trainNumber:'',
    coachType:'',
    sourceCity:'',
    destinationCity:'',
    dateOfJourney:'',
    bookingType:'',
  }
  
  ngOnInit(): void {
    
    // if(this.item){
    //   this.ticketDatainfo=this.item
    // }
    console.log(this.item)
  }
 

}
