import { Component, OnInit } from '@angular/core';

import { UpdatetrainService } from '../../services/updatetrain.service';

export interface Train {
  trainNumber: '';
  trainName: '';
  trainType: '';
  totalDistance: '';
  scheduledDays: [];
  coachDTO: [{ coachNumber: '',coachType: '',coachNormalFare:'',coachTaktalFare:'',coachPremiumTaktalFare:'',seats:[{seatNumber:'',seatType:''}] }]; // <-- Include this line
  stationDTO: [{stationName:'',city:'',state:'',noOfPlatform:'',isSource:'',timeOfArrival:'',timeOfDeparture:'',distanceBetweenDestination:''}];
  sourceCity: '';
  destinationCity: '';
}
export interface CoachDTO {
  // Define properties specific to CoachDTO
  coachNumber:'',
  coachType:'',
  coachNormalFare:'',
  coachTaktalFare:'',
  coachPremiumTaktalFare:'',
  seats: [{ seatNumber: '', seatType: '' }];

}

export interface StationDTO {
  // Define properties specific 
  stationName:'',
  city:'',
  state:'',
  noOfPlatform:'',
  isSource:'',
  timeOfArrival:'',
  timeOfDeparture:'',
  distanceBetweenDestination:''

}





@Component({
  selector: 'app-updatetrain',
  templateUrl: './updatetrain.component.html',
  styleUrls: ['./updatetrain.component.css']
})
export class UpdatetrainComponent implements OnInit {

public Train: Train = {
    trainNumber: '',
    trainName: '',
    trainType: '',
    totalDistance: '',
    scheduledDays: [],
    coachDTO: [{ coachNumber: '',coachType: '',coachNormalFare:'',coachTaktalFare:'',coachPremiumTaktalFare:'',seats:[{seatNumber:'',seatType:''}] }],
    stationDTO: [{stationName:'',city:'',state:'',noOfPlatform:'',isSource:'',timeOfArrival:'',timeOfDeparture:'',distanceBetweenDestination:''}],
    sourceCity: '',
    destinationCity: ''
  };

  
  

  daysOfWeek: string[] = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];

  constructor(private updatetrainService:UpdatetrainService){}
  ngOnInit(): void {
    // let response = this.trainDataService.updateTrain();
    // response.subscribe((data:any) => this.trains = data);
    this.updateTrain();
  }
  updateTrain()
  {
    
    console.log('Updating train:', this.Train);
    this.updatetrainService.updateTrain(this.Train).subscribe(
    (data) => {
      console.log(data);
      alert("success");
      window.location.href="/ManageTrain"
    },
    (error) => {
      // Handle errors if any
      console.error('Error updating train:', error);
    })
  }
}
 
  


