import { Component, OnInit } from '@angular/core';
import { TrainDataService } from '../../services/train-data.service';
export interface Train{
  trainId: '';
  trainNumber: '';
  trainName: '';
  source: '';
  destination: '';
  totalDistance: '';
};
@Component({
  selector: 'app-available-train',
  templateUrl: './available-train.component.html',
  styleUrl: './available-train.component.css'
})


export class AvailableTrainComponent implements OnInit {
  
  trains: Train[] = [];
  

  
  
  constructor(private trainDataService:TrainDataService){
    
  }
  ngOnInit():void{
    let response = this.trainDataService.getTrainsall();
    response.subscribe((data:any) => this.trains = data);
  }

}
