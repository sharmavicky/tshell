import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './environment';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class ResetService {
  url: string = environment.serviceUrlPrefix + '/reset';
  constructor(private http: HttpClient) { }
  reset(json): Observable<any> {
    return this.http.post<any>(this.url, json , httpOptions);
  }

}
