import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const httpOptions={
  headers: new HttpHeaders({
    'Content-Type':'application/json',
  })
}

@Injectable({
  providedIn: 'root'
})
export class DeactivateActivateSkillService {
  url: string = "/tShell/skill/list/";
  url1: string = "/tShell/skill/save";
  constructor(private http: HttpClient) { }

  gettingSkill(name): Observable<any>{  
    console.log(name);
      return this.http.get<any>(this.url+name);
  }

  toggleSkill(id): Observable<any>{
    return this.http.post<any>(this.url1,id,httpOptions);
  }
}
  

