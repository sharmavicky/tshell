import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../app/environment';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
}

@Injectable({
  providedIn: 'root'
})
export class ViewprofileService {

  url = environment.serviceUrlPrefix + "/user/getUser/";
  url1 = environment.serviceUrlPrefix + "/assessment/getAssessment/";
  url2 = environment.serviceUrlPrefix + "/user/save";
  url3 = environment.serviceUrlPrefix + "/user/getRoles";
  url4 = environment.serviceUrlPrefix + "/user/update";
  url5 = environment.serviceUrlPrefix + "/user/saveskill";
  skillnames: string = environment.serviceUrlPrefix + "/user/getSkillsOnSearch/";

  constructor(private http: HttpClient) { }

  getUserDetails(employeeId): Observable<any> {
    return this.http.get<any>(this.url + employeeId);
  }

  getUserAssessment(employeeId): Observable<any> {
    return this.http.get<any>(this.url1 + employeeId);
  }

  save(json): Observable<any> {
    console.log(json);
    return this.http.post<any>(this.url2, json, httpOptions);
  }

  getRole(): Observable<any> {
    return this.http.get<any>(this.url3);
  }
  updateUser(user): Observable<any> {
    console.log("inside updateUser");
    console.log(user);
    return this.http.post<any>(this.url4, user, httpOptions);
  }

  skillsOnSearch(name): Observable<any> {
    return this.http.post<any>(this.skillnames, name, httpOptions);
  }

  saveskills(user) {
    return this.http.post<any>(this.url5, user, httpOptions);
  }
}
