import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../app/environment';

@Injectable({
  providedIn: 'root'
})
export class SkillService {
  url: string = environment.serviceUrlPrefix + "/skill/recentskilllist/"
  url2: string = environment.serviceUrlPrefix + "/skill/skillbyname/"
  skillToppersUrl = environment.serviceUrlPrefix + '/assessment/top5list/';
  constructor(private http: HttpClient) { }

  getrecentSkill(): Observable<any> {

    console.log("in skillService");
    console.log(this.url);
    return this.http.get<any>(this.url);

  }

  getSkillbyName(name: any): Observable<any> {

    console.log("in skillService");
    console.log(name);
    return this.http.get<any>(this.url2 + name);

  }

  getSkillTopper(id): Observable<any> {
    console.log(this.skillToppersUrl + id);
    return this.http.get(this.skillToppersUrl + id);
  }
}
