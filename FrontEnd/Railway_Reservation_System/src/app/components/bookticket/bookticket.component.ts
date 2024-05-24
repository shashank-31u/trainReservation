import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { BookingDetailsService } from '../../services/booking-details.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import moment from 'moment';



export interface BookingResponse{
  bookingId:'',
  bookingStatus:'',
  message:'',
  fare:'',
  bookingType:''
}
export interface PassengerDTOList{
  passengerName:'',
  passengerAge:'',
  gender:''

}
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
  selector: 'app-bookticket',
  templateUrl: './bookticket.component.html',
  styleUrl: './bookticket.component.css'
})

export class BookticketComponent implements OnInit {
  

  
  // bookingId:String=''
  ticketData:any
  showBookingInfo=false
  tableHead=['PNR-ID','Booking Id','Booking Status','Source City','Destination City','Train Name','rain Number','Date','Actions']
  sortedDTO: any[] = [];
   




  timeControl = new FormControl();
  responseData: BookingResponse | undefined ;
  Details={
    trainNumber:'',
    coachType:'',
    sourceCity:'',
    destinationCity:'',
    dateOfJourney:'',
    bookingType:'',
    passengerDTOList: [{ passengerName: '',passengerAge: '',gender:''}],

  }
  body={
    bookingId: '',
    trainNumber: '',
    coachType:'',
    sourceCity:'',
    destinationCity:'',
    dateOfJourney:''
  }
  dateForm!: FormGroup
  showBookingButton=false;
  constructor(private bookingDetailsService :BookingDetailsService,private formBuilder: FormBuilder){}
  ngOnInit(): void {
    const date = new Date()
    this.dateForm = this.formBuilder.group({
      dateOfJourney: ['',new Date(moment(date).format('YYYY-MM-DD')),[Validators.required]],
      trainNumber:['',[Validators.required]],
      coachType:['',[Validators.required]],
      sourceCity:['',[Validators.required]],
      destinationCity:['',[Validators.required]],
      bookingType:['',[Validators.required]],
      passengerName:['',[Validators.required]],
      passengerAge:['',[Validators.required]],
      gender:['',[Validators.required]]
      

    })
  }
  addBooking()
  {
    this.Details.dateOfJourney = this.dateForm.value.dateOfJourney
    this.Details.trainNumber = this.dateForm.value.trainNumber
    this.Details.coachType = this.dateForm.value.coachType
    this.Details.sourceCity = this.dateForm.value.sourceCity
    this.Details.destinationCity = this.dateForm.value.destinationCity
    this.Details.bookingType = this.dateForm.value.bookingType
    this.Details.passengerDTOList[0].passengerName = this.dateForm.value.passengerName
    this.Details.passengerDTOList[0].passengerAge = this.dateForm.value.passengerAge
    this.Details.passengerDTOList[0].gender = this.dateForm.value.gender
    this.bookingDetailsService.addBooking(this.Details).subscribe({
      next: (res: BookingResponse) => {
        if (res) {
          let jsonString = JSON.stringify(res);
          this.responseData = JSON.parse(jsonString);
          console.log('Booking Response:', this.responseData);
          console.log("Api success")
        } else {
          console.log("Api failure")
        }
      },
      error: () => {
        console.log("something went wrong")
      }
    })


    
    
      // console.log("method working",this.Details);
      // this.bookingDetailsService.addBooking(this.Details).subscribe(
      //   (data:BookingResponse)=>{
      //     let jsonString = JSON.stringify(data);
      //     this.responseData=JSON.parse(jsonString);
      //     console.log('Booking Response:', this.responseData);
      //   // window.location.href="/Booking"
      //   },
      //   errors=>{
      //     console.log(errors);
      //   }

      // )
      // console.log("method working",this.Details);
    

  }
  saveBooking(){
    const isPaymentDone = true;
    const bookingId=this.responseData?.bookingId?this.responseData.bookingId:''
    
    this.bookingDetailsService.saveBooking(bookingId, isPaymentDone).subscribe({
      next: (res:any) => {
        if (res) {
          let jsonString = JSON.stringify(res);
          this.ticketData = JSON.parse(jsonString);
          this.sortedData()
          this.showBookingInfo=true
          console.log('Booking saved successfully:', res);
        } else {
          console.log("Api failure")
        }
      },
      error: () => {
        console.log("something went wrong")
      }
    })     
  }
  sortedData() {
    if (this.ticketData) {
      this.sortedDTO.push({
        "pnr": this.ticketData.pnr,
        "bookingId": this.ticketData.bookingId,
        "bookingStatus": this.ticketData.bookingStatus,
        "sourceCity": this.ticketData.sourceCity,
        "destinationCity": this.ticketData.destinationCity,
        "trainName": this.ticketData.trainName,
        "trainNumber": this.ticketData.trainNumber,
        "dateOfJourney": this.ticketData.dateOfJourney
      });
      console.log(this.sortedDTO);
    } else {
      console.error('Data is not available.');
    }
  }
  backTobooking() {
    this.showBookingInfo=false
  }

  CancelBooking(item:any){
    const pnr=item.pnr
    const body={
      "bookingId": item.bookingId,
      "trainNumber": item.trainNumber,
      "coachType": this.ticketData.passengerOutboundDTOList[0].coachType,
      "sourceCity": item.sourceCity,
      "destinationCity": item.destinationCity,
      "dateOfJourney": item.dateOfJourney
    };
    
    console.log(body)
    this.bookingDetailsService.cancelBooking(pnr, body).subscribe({
      next: (res:any) => {
        if (res) {
          console.log('Booking cancel successfully:', res);
          // Remove the canceled item from sortedDTO
          this.sortedDTO = this.sortedDTO.filter((sortedItem: any) => sortedItem.pnr !== pnr);
          // this.saveBooking()
        } else {
          console.log("Api failure")
        }
      },
      error: () => {
        console.log("something went wrong")
      }
    }); 

  }

  // const bookingId=this.responseData?.bookingId?this.responseData.bookingId:''
// this.body.bookingId = item.bookingId,
      // this.body.trainNumber = item.trainNumber,
      // this.body.coachType = this.ticketData.passengerOutboundDTOList[0].coachType,
      // this.body.sourceCity = item.sourceCity,
      // this.body.destinationCity = item.destinationCity,
      // this.body.dateOfJourney = item.dateOfJourney

 


}
