import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {FormGroup, FormControl} from '@angular/forms';  
import {MatDatepickerModule} from '@angular/material/datepicker';
import { SearchtrainsService } from '../../services/searchtrains.service';
// export interface Station{
//   sourceTimeOfArrival:'';
//   destinationTimeOfArrival:'';
// }
export interface TrainData{
  trainId:'',
  trainNumber:'',
  trainName:'',
  sourceCity:'',
  destinationCity:'',
  distanceBetweenSourceDestination:'',
  sourceTimeOfArrival:'';
  destinationTimeOfDeparture:''
}
  // timeOfArrival: String;
  // timeOfDeparture: String;

  // stations: Station[];

@Component({
  selector: 'app-search-trains',
  templateUrl: './search-trains.component.html',
  styleUrl: './search-trains.component.css' 
})
export class SearchTrainsComponent implements OnInit {

trains: TrainData[] = [];
router: any;

// TrainData:TrainData[]=TrainData;
// displayedColumns: String[]=["TrainId","TrainNo","TrainName","TrainFrom","TrainTo","Distance","Source Arrival Time","Destination Arrival Time"]
// dataSource= ELEMENT_DATA;

// exampleHeader: ComponentType<any>;
  constructor( private searchtrainsService: SearchtrainsService,
    private formBuilder: FormBuilder){

  }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  

  public search={
    sourceCity:'',
    destinationCity:'',
    dateOfJourney:''

  }
  searchTrains() {
    if((this.search.sourceCity !="" && this.search.destinationCity !="") && (this.search.sourceCity!=null && this.search.destinationCity!=null)){
      console.log("method working")
      this.searchtrainsService.searchTrains(this.search).subscribe(
        (data: TrainData[]) => {
          this.trains = data;
          console.log('Trains:', this.trains);
        },
        errors=>{
          console.log(errors);
        })
    
    
  }
  
  
 
  

  }
}
