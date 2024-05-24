import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TrainData } from '../components/managetrain/managetrain.component';


@Injectable({
  providedIn: 'root'
})
export class TrainDataService {
  url="http://localhost:8004"
  constructor(private http:HttpClient) { }

  getTrainsall() {
    return this.http.get(`${this.url}/api/train/getAllTrainDetails`);
  }

  
  deleteTrain(trainNumber:any){
    return this.http.delete(`${this.url}/api/train/deleteTrainInfo`,trainNumber);
  }

  // public saveBooking(bookingId: any, isPaymentDone: Boolean) {
  //   return this.http.post(`${this.url}/api/booking/saveBooking?isPaymentDone=${isPaymentDone}`,{'bookingId':bookingId});
  // }
}
