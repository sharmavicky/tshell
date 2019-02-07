import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from './environment';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
}

@Injectable({
  providedIn: 'root'
})
export class CountOfPendingQuestionsService {

  skillId:any;
  skillName:any;

  constructor(private http: HttpClient) { }

  count: string = environment.serviceUrlPrefix + "/questionHome/getcountReviewQ/";
  skillnames: string = environment.serviceUrlPrefix + "/questionHome/getSkillsOnSearch/";

  CountReviewQ(): Observable<any[]> {
    return this.http.get<any[]>(this.count);
  }

  skillsOnSearch(name): Observable<any> {
    return this.http.post<any>(this.skillnames, name, httpOptions);
  }
}
