import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Train } from '../components/updatetrain/updatetrain.component';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UpdatetrainService  implements OnInit{
  url="http://localhost:8004"
  constructor(private http:HttpClient) { }
  ngOnInit(): void {
    
  }
  addTrain(train:Train):Observable<any>{
    return this.http.post(`${this.url}/api/train/addTrainInfo`,train);

  }
  



  updateTrain(train:Train):Observable<any>{
    return this.http.put(`${this.url}/api/train/updateTrainInfo`,train);
  }
}
