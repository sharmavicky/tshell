import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment} from '../app/environment'

@Injectable({
  providedIn: 'root'
})
export class AppinfoService {

  url:string=environment.serviceUrlPrefix + "/skill/skillcount/"
  url2:string=environment.serviceUrlPrefix + "/user/usercount/"
  url3:string=environment.serviceUrlPrefix + "/question/questioncount/"
  url4:string=environment.serviceUrlPrefix + "/assessment/assessmentcount/"
  constructor(private http: HttpClient) { }

  getSkillCount(): Observable<any> {
    console.log("inside service")
    console.log(this.url)
    return this.http.get<any>(this.url);
  }

  getUserCount(): Observable<any> {
    console.log("inside service")
    console.log(this.url2)
    return this.http.get<any>(this.url2);
  }

  getQuestionCount(): Observable<any> {
    console.log("inside service")
    console.log(this.url3)
    return this.http.get<any>(this.url3);
  }

  getAssessmentCount(): Observable<any> {
    console.log("inside service")
    console.log(this.url4)
    return this.http.get<any>(this.url4);
  }
}
