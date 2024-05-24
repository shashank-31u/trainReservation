import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingDetailsService {
  url="http://localhost:8003"

  constructor(private http: HttpClient) { }

  public addBooking(Details:any): Observable<any> {
    return this.http.post(`${this.url}/api/booking/createBooking`,Details);
  }

  public saveBooking(bookingId: any, isPaymentDone: Boolean) {
    return this.http.post(`${this.url}/api/booking/saveBooking?isPaymentDone=${isPaymentDone}`,{'bookingId':bookingId});
  }

  public cancelBooking(pnr:any,body: any) {
    // const queryParams = new HttpParams({ fromObject: body });
    // return this.http.get(`${this.url}/api/booking/cancelBooking?pnr=${pnr}&${queryParams.toString()}`);
    return this.http.post(`${this.url}/api/booking/cancelBooking?pnr=${pnr}`,body);
  }

  //   const options = {
  //     headers: { 'Content-Type': 'application/json' },
  //     body: body,  // Include the body here
  //   };

  //   return this.http.request('DELETE', `${this.url}/api/booking/cancelBooking?pnr=${pnr}`, options);
  // }
}

