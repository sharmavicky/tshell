import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../app/environment';


@Injectable({
  providedIn: 'root'
})

export class  TotalQuestionsService {
  employeeId :any =[];
  url: string = environment.serviceUrlPrefix + "/question/contributed/";




  constructor(private http: HttpClient) { }

  totalquestion(employeeId): Observable<any> {
    console.log(employeeId);
    return this.http.get<any>(this.url + employeeId);
  }

}
