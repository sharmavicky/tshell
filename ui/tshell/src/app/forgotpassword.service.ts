import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../app/environment';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class ForgotpasswordService {

  constructor(private http: HttpClient) { }
  requestPasswordReseturl = environment.serviceUrlPrefix + "/user/requestpasswordreset/";
  verificationurl: string = environment.serviceUrlPrefix + "/user/link-verification/";
  otpverification: string = environment.serviceUrlPrefix + "/user/verifyotp/";
  resetPasswordUrl: string = environment.serviceUrlPrefix + "/user/resetPassword/";
  requestPasswordReset(employeeid): Observable<any> {
    console.log(this.requestPasswordReseturl + employeeid);
    return this.http.get<any>(this.requestPasswordReseturl + employeeid, { responseType: 'json' });
  }


  submitOtp(employeeid, encryptOtp): Observable<any> {
    console.log(this.otpverification + employeeid + "/" + encryptOtp);
    return this.http.get<any>(this.otpverification + employeeid + "/" + encryptOtp, httpOptions);
  }

  resetPassword(employeeid, encryptedPassword): Observable<any> {
    console.log(this.resetPasswordUrl + employeeid + "/" + encryptedPassword);
    return this.http.get<any>(this.resetPasswordUrl + employeeid + "/" + encryptedPassword, httpOptions);
  }

}
