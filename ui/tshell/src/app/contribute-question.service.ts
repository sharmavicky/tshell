import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { environment } from '../app/environment';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
}
@Injectable({
  providedIn: 'root'
})

export class ContributeQuestionService {
  csvData: any;
  topicsUrl: string = environment.serviceUrlPrefix + "/question/getTopics/";
  userDetailsUrl: string = environment.serviceUrlPrefix + "/question/getuserdata/";
  uploadUrl: string = environment.serviceUrlPrefix + '/question/upload';
  addQuestionUrl: string = environment.serviceUrlPrefix + '/question/addQuestion';
  submitForReviewUrl: string = environment.serviceUrlPrefix + '/question/submitforreview/';
  approveSubmittedUrl: string = environment.serviceUrlPrefix + '/question/approveandsubmit/';

  constructor(private http: HttpClient) {
    this.http = http;
  }

  addQuestion(json): Observable<any> {
    return this.http.post<any[]>(this.addQuestionUrl, json, httpOptions);
  }
  uploadQuestions(formData: any): Observable<any> {
    return this.http.post<any>(this.uploadUrl, formData);
  }
  submitForReview(employeeId): Observable<any> {
    console.log('approved() is called');
    return this.http.get<any>(this.submitForReviewUrl + employeeId);
  }
  approveSubmittted(employeeId): Observable<any> {
    console.log('approved() is called');
    return this.http.get<any>(this.approveSubmittedUrl + employeeId);
  }
  getCsvData() {
    return this.csvData;
  }
  setCsvData(csvData: any) {
    this.csvData = csvData;
  }
  getTopics(skillId): Observable<any[]> {
    return this.http.get<any[]>(this.topicsUrl + skillId);
  }
  getUserDetails(employeeId): Observable<any[]> {
    return this.http.get<any[]>(this.userDetailsUrl + employeeId);
  }
}
