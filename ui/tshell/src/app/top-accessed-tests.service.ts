import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../app/environment';

const httpOptions={
  headers: new HttpHeaders({
    'Content-Type':'application/json',
  })
}

@Injectable({
  providedIn: 'root'
})
export class TopAccessedTestsService {
  url:string = environment.serviceUrlPrefix + "/skill/gettop5tests/"
  constructor(private http:HttpClient) { } 
  
  getTestDetails():Observable<any> {
    console.log("inside service");
    console.log(this.url);
    return this.http.get<any>(this.url)
  }
}