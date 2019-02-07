import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environment';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};
@Injectable({
    providedIn: 'root'
})
export class SignupService {
    url: string = environment.serviceUrlPrefix + "/signup/"
    url1: string = environment.serviceUrlPrefix + "/requestSignupOtp"
    url2: string = environment.serviceUrlPrefix + "/verifySignupOtp"
    url3: string = environment.serviceUrlPrefix + "/validateOtpTime"
    url4: string = environment.serviceUrlPrefix + "/regenerateOtp"
    constructor(private http: HttpClient) { }

    signup(json): Observable<any> {
        return this.http.post<any>(this.url, json, httpOptions);
    }

    requestSignupOtp(employeeId): Observable<any> {
        return this.http.post<any>(this.url1, employeeId, httpOptions);
    }

    verifySignupOtp(user): Observable<any> {
        return this.http.post<any>(this.url2, user, httpOptions);
    }
    validateOtpTime(user): Observable<any> {
        return this.http.post<any>(this.url3, user, httpOptions);
    }
    regenerateOtp(user): Observable<any> {
        return this.http.post<any>(this.url4, user, httpOptions);
    }



}