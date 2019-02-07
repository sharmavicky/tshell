import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { LoginService } from 'src/app/login.service';
import { FormGroup, FormControl, Validators } from '@angular/forms'
import { ForgotpasswordService } from '../forgotpassword.service';
import { Md5 } from "md5-typescript";
import { dashCaseToCamelCase } from '@angular/compiler/src/util';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  error = false;
  numberPattern = "^[0-9]{6}$";
  message: string;
  status: boolean = false;
  success = true;
  resentmessage:string;
  resentstatus=false;



  formStatus: boolean = false;
  employeeId: number;
  otpStatus: boolean = false;
  resetStatus: boolean = false;
  constructor(private router: Router, public service: AuthService, public forgotpasswordservice: ForgotpasswordService, private loginService: LoginService) { }

  form = new FormGroup({
    employeeId: new FormControl(
      '', [Validators.required,

      Validators.minLength(5)

      ]),

    password: new FormControl(
      '', [Validators.required
      ])
  });

  resetRequestForm = new FormGroup({
    employeeid: new FormControl(
      '',
      [Validators.required,
      Validators.minLength(6),
     Validators.maxLength(10),
      ]

    )
  });
  resetform = new FormGroup({
    newpassword: new FormControl(
      '', [Validators.required,
        Validators.minLength(6),
         Validators.maxLength(30)
     ]
    ),

    confirmPassword: new FormControl(
      '', [Validators.required,
        Validators.minLength(6),
         Validators.maxLength(30)
     ]
    ),
  })

  otpform = new FormGroup({
    otp: new FormControl(
      '',
      [Validators.required,
      Validators.pattern(this.numberPattern)
      ]

    )
  });

  ngOnInit() {
    console.log("inside login");
  }

  check(employeeId: string, password: string) {
    console.log("Inside check() function");
    let json = JSON.stringify({
      employeeId: employeeId,
      password: password
    });
    console.log(json);
    this.loginService.authenticateUser(json)
      .subscribe(data => {

        console.log("incoming Data: " + data)
        console.log("incoming Data: " + data.authenticated)
        console.log("incoming admin or not? : " + data.admin)

        if (data.authenticated) {
          this.service.login();
          this.service.setRole(data.user.role.name);
          this.service.setEmployeeId(data.user.employeeId);

          this.router.navigate(['/dashboard']);

        }

        if (data.admin) {
          console.log("inside Admin Signup Component");
          this.router.navigate(['/authenticate']);
        }


        else {
          this.success = false;
          this.error = false;

        }
      },

      error => {
        this.error = error;
        this.success = true;
        console.log(this.error);
      }

      );
  }


  sendMail() {
    console.log('This is check!');
    this.message = "Mail sent";
    this.status = true;
  }
  requestPasswordReset(employeeid) {
    this.employeeId = employeeid;
    this.forgotpasswordservice.requestPasswordReset(employeeid).subscribe(
      data => {
        console.log(data);
        if (data == true) {
          this.status = true;
        }
        else{
          this.message="User doesn't exists"
        }
      },
      error => {
        this.error = true;
      }
    );
    
    
  }



  resendOtp() {
    this.forgotpasswordservice.requestPasswordReset(this.employeeId).subscribe(
      data => {
        console.log(data);
        if (data == true) {
          this.resentmessage="OTP resent successfully";
          this.status = true;
          this.resentstatus=true;
          this.message="";
          this.otpform.reset();
        }
      }
    );
  }

  close() {
    this.message = "";
    this.resentmessage="";
    this.status = false;
    this.resetStatus = false;
    this.otpStatus = false;
    this.resetRequestForm.reset();
    this.otpform.reset();
    this.resetform.reset();
    this.error = false;
    this.resentstatus=false;
  }
  submitOtp() {
    console.log(this.employeeId);
    let e = Md5.init(this.otpform.value.otp);
    this.forgotpasswordservice.submitOtp(this.employeeId, e).subscribe(
      data => {
        console.log(data);
        if (data == true) {
          this.message = "";
          this.status = true;
          this.otpStatus = true;
          this.resentstatus=false;
        }
        else {
          this.message = "OTP is incorrect or expried";
          this.resentstatus=false;
        }
      }
    );
  }
  resetPassword() {

    let encryptedPassword = Md5.init(this.resetform.value.newpassword);
    this.forgotpasswordservice.resetPassword(this.employeeId, encryptedPassword).subscribe(
      data => {
        console.log(data);
        if (data == true) {
          this.status = true;
          this.resetStatus = true;
          this.message = "";
          //alert("Password changed successfully");
        }
        else {
          this.message = "issue in seting the password"
        }
      }
    );
  }

}

