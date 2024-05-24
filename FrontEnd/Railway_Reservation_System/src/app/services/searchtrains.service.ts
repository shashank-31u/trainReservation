import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchtrainsService {
  url="http://localhost:8004"

  // url1="http://localhost:8004"
  constructor(private http: HttpClient) { }

  public searchTrains(search:any): Observable<any> {
    return this.http.post(`${this.url}/api/train/searchTrain`,search);
  }

  public getAllTrains(){
    return this.http.get(`${this.url}/api/train/getAllTrainDetails`);
  }


}
