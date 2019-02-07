import { Injectable } from '@angular/core';
import { environment } from './environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders(
    {
      'Content-Type': 'application/json',
    }
  )
};
@Injectable({
  providedIn: 'root'
})

export class AssessmentScoreService {

  topicWiseScoreUrl = environment.serviceUrlPrefix + '/assessment/topicwisescore/';

  constructor(private http: HttpClient) { }

  getTopicWiseScore(assessmentId): Observable<any> {
    return this.http.get(this.topicWiseScoreUrl + assessmentId);
  }

}
