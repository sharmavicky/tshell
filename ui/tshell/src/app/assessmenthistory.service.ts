import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-type': 'application/json',
  })
};
@Injectable({
  providedIn: 'root'
})
export class AssessmenthistoryService {
url="";
  constructor(private http:HttpClient) { }
  hisrory: any[]=[];

  getAssessmentDetails() : Observable<any[]> {
    return this.http.get<any[]>(this.url);
   }
}
/*import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class FlightserviceService {
  url: string = "/EclipseFlightBooking/rest/city/list1";
  url2: string ="/EclipseFlightBooking/rest/city/show/"
  constructor(private http:HttpClient) { }

  flights: any[]=[];

  getFlights() : Observable<any[]> {
    return this.http.get<any[]>(this.url);
   }
   getFlightsBetween(source,dest,date) : Observable<any> {    
    return this.http.get<any>(this.url2+source+'/'+dest+'/'+date,httpOptions);
 
   }
}
*/ 