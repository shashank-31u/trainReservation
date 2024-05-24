import { Component, OnInit } from '@angular/core';
import { SearchtrainsService } from '../../services/searchtrains.service';
import { TrainDataService } from '../../services/train-data.service';
import Swal from 'sweetalert2';
export interface TrainData{
  trainNumber:'',
  trainName:'',
  trainType:'',
  totalDistance:'',
  scheduledDay: string[];
  source:'',
  destination:''
  // coachDTO: Coach[];
  // stationDTO: Station[];
}
// export interface Coach {
//   coachNumberId:'',
//   coachType:'',
//   coachNormalFare:'',
//   coachTaktalFare:'',
//   coachPremiumTaktalFare:'',


// }
// export interface Station {
//   stationName:'',
//   city:'',
//   state:'',

// }
@Component({
  selector: 'app-managetrain',
  templateUrl: './managetrain.component.html',
  styleUrl: './managetrain.component.css'
})
export class ManagetrainComponent implements OnInit{

  trains: TrainData[] = [];
  router: any;
  
  constructor(private trainDataService: TrainDataService){}
  ngOnInit(): void {
    let response = this.trainDataService.getTrainsall();
    response.subscribe((data:any) => this.trains = data);
  }
  
  
  
  public deleteTrain(trainNumber: any) {
    // const bookingId=this.responseData?.bookingId?this.responseData.bookingId:''
    // const trainNumber=this.trains?.trainNumber?this.trains.trainNumber:''
    Swal.fire({
      title: 'Are you Sure?',
      text: '',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'Cancel'
    }).then((result: any) => {
      if (result.value) {
        const requestBody = { key: trainNumber };
        let response = this.trainDataService.deleteTrain(requestBody);
        response.subscribe(
          (data: any) => {
            this.trains = data;
            alert("Train deleted Successfully");
          },
          (error: any) => {
            console.log('Error deleting train:', error);
          }
        );
        
       
      }
    });
  }
}