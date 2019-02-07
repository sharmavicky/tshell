import { Component, OnInit } from '@angular/core'
import { Router } from '../../../node_modules/@angular/router';
import { SignupService } from './signup.service';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { VALID } from '../../../node_modules/@angular/forms/src/model';
import { THIS_EXPR } from '../../../node_modules/@angular/compiler/src/output/output_ast';
import { User, Role } from '../user';
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  emailPattern = "^[a-zA-Z0-9._%+-]+@[A-Za-z0-9.-]+\.[a-zA-Z]{2,4}$";
  // employeeIdPattern = "^(0|[1-9][0-9]*)$";
  //passwordPattern = "^([a-zA-Z0-9@*#!?]{8,12})$";
  empId: any;
  message = "";
  json: any;
  error: any;
  userRole: any;
  isDefaultAdmin = false;
  otp: any;
  status: any = {
    emailExist: false,
    userIdExist: false,
    signupStatus: false,
    otpVerifyStatus: false,

  };
  currentMode: any = {
    signupMode: true,
    emailVerifyMode: false,
    otpVerifyMode: false,

  }
  verifiedMode: any = {

    success: false,
    failure: false,

  }

form = new FormGroup({
    employeeId: new FormControl(
      '', [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(10),
        //Validators.pattern(this.employeeIdPattern)
      ]

    ),
    name: new FormControl(
      '', [Validators.required,
      Validators.maxLength(45)
      ]
    ),

    email: new FormControl(
      '', [Validators.required,
      Validators.pattern(this.emailPattern)],

    ),

    password: new FormControl(
      '', [Validators.required,
      Validators.minLength(6),
      Validators.maxLength(30)
      ]

    ),

    confirmPassword: new FormControl(
      '', Validators.required
    ),
  })



  otpform = new FormGroup({
    otp: new FormControl(
      '',
      Validators.required
    ),
  })
  constructor(private router: Router, private signupService: SignupService, private authService: AuthService) { }


  ngOnInit() {

    this.isDefaultAdmin = this.authService.isDefaultAdmin;
    console.log("is he admin?? :" + this.isDefaultAdmin)


  }

  signup() {

    if (this.isDefaultAdmin) {
      this.userRole = new Role("Admin");
    } else if (!this.isDefaultAdmin) {
      this.userRole = new Role("Learner");
    }

    const user = new User(this.form.controls['employeeId'].value, null, this.form.controls['name'].value, this.empId = this.form.controls['email'].value, this.userRole, this.empId = this.form.controls['password'].value)
    /*  this.json = this.form.value; */
    console.log("The user json: " + user);
    this.empId = this.form.controls['employeeId'].value;
    this.signupService.signup(user).subscribe(
      data => {
        console.log(data);
        this.status = data;
        console.log("Email :" + data.emailExist);
        console.log("Emp Id: " + data.userIdExist);
        console.log("signup status :" + data.signupStatus)
        console.log("otp status :" + data.otpVerifyStatus)
        this.form.reset();
        /*  if(data.signupStatus == false){
           this.currentMode.signupMode=true;
           this.currentMode.emailVerifyMode=false;  
         } */
        if (data.signupStatus == true && data.otpVerifyStatus == false) {
          this.currentMode.signupMode = false;
          this.currentMode.emailVerifyMode = true;
        }
        if (data.signupStatus == false && data.otpVerifyStatus == false && data.userIdExist == true && data.emailExist == false) {
          this.currentMode.signupMode = true;
          this.currentMode.emailVerifyMode = false;
        }
        if (data.signupStatus == false && data.otpVerifyStatus == false && data.userIdExist == false && data.emailExist == false) {
          this.currentMode.signupMode = false;
          this.currentMode.emailVerifyMode = true;

        }
        if (data.signupStatus == false && data.otpVerifyStatus == false && data.emailExist == false) {
          this.currentMode.signupMode = true;
          this.currentMode.emailVerifyMode = false;
        }
        if (data.signupStatus == false && data.otpVerifyStatus == false && data.emailExist == true && data.userIdExist == true) {
          this.currentMode.signupMode = false;
          this.currentMode.emailVerifyMode = true;
          this.message = "Your details have been already saved. Only OTP will be generated"
        }
        console.log(this.currentMode.signupMode);
        console.log(this.currentMode.emailVerifyMode)
      },
      error => {
        this.error = error;
        console.log(this.error);
      }
    );
  }
  verifyEmail() {
    //this.showOtp=true;
    this.currentMode.signupMode = false;
    this.currentMode.emailVerifyMode = false;
    this.currentMode.otpVerifyMode = true;


    console.log(this.empId);
    this.signupService.requestSignupOtp(this.empId).subscribe(
      data => {
        console.log(data);
      }
    );

  }

  otpVerify() {
    this.currentMode.signupMode = false;
    this.currentMode.emailVerifyMode = false;
    this.currentMode.otpVerify = true;
    this.otp = this.otpform.controls['otp'].value;
    const user = new User(this.empId, this.otp, null, null, null, null);
    this.signupService.validateOtpTime(user).subscribe(
      data => {
        console.log(data);
        if (data) {
          this.signupService.verifySignupOtp(user).subscribe(
            data => {
              console.log(data);
              if (data) {

                this.currentMode.otpVerifyMode = false;
                this.verifiedMode.success = true;
                this.verifiedMode.time = false;
              }
              else {

                this.currentMode.otpVerifyMode = true;
                this.verifiedMode.failure = true;
                this.verifiedMode.time = false;
                this.otpform.reset();
              }
            }
          )
        }
        else {
          this.verifiedMode.time = true;
          this.otpform.reset();
        }
      }
    )
    this.verifiedMode.failure = false;


  }

  regenerateOtp() {
    this.otp = this.otpform.controls['otp'].value;
    const user = new User(this.empId, this.otp, null, null, null, null);
    this.signupService.regenerateOtp(user).subscribe(
      data => {
        console.log("Regenerated Otp" + data);
      })

  }


}