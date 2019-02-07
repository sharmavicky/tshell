import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ChangePassword } from './ChangePassword/changepassword';
import {environment} from './environment';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};



@Injectable({
  providedIn: 'root'
})
export class ChangepasswordService {
  
  url = environment.serviceUrlPrefix+"/user/changepassword";

  constructor(private http: HttpClient) { }

   savepassword(employeeId, currentPassword, newPassword ): Observable<any> {
     console.log("inside the save password service");
     console.log(this.url+'/'+employeeId+'/'+currentPassword+'/'+newPassword);
     return this.http.get<any>(this.url+'/'+employeeId+'/'+currentPassword+'/'+newPassword, httpOptions);
   }
}
