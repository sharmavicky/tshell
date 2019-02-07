import { Component, OnInit,ElementRef, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { PasswordValidators } from './password.validation';
import { ChangepasswordService } from '../changepassword.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css']
})
export class ChangepasswordComponent implements OnInit {
  form:any;
  /* @ViewChild('exampleModal') exampleModal:ElementRef;

  passwordPattern ="/^[A-Za-z]{4,20}/";

  empid: any;
  password: any;
  user:any;
  newpassword:any;
  public message: string;

  form = new FormGroup({
    oldpassword: new FormControl(
      '',
      [Validators.required,
      Validators.minLength(4),
      Validators.maxLength(15),
      PasswordValidators.cannotContainSpace
      ]),
    newpassword: new FormControl(
      '',
      [Validators.required,
      Validators.minLength(4),
      Validators.maxLength(15),
      Validators.pattern(this.passwordPattern)]
      ),


    confirmpassword: new FormControl(
      '',
      [Validators.required,
      Validators.minLength(4),
      Validators.maxLength(15),
      Validators.pattern(this.passwordPattern)
    ]),

  }); */

  constructor(private router : Router) { }
  /* private service: ChangepasswordService */
  ngOnInit() {
    /* this.exampleModal.nativeElement.click(); */
  }
  
/* 
  getData() {
    console.log("inside the getData");
    this.service.getDetails(this.empid).subscribe(
      data => {
        console.log(data);
        this.user = data;
        
      })
  }*/

 /*  savePassword(oldpassword: string , newpassword : string, confirmpassword : any){
    if(oldpassword == "Rahul@01" && newpassword=="vishal" && confirmpassword=="vishal"){
      console.log(oldpassword);
      this.message = "Incorrect Old Password";  
      this.router.navigate(['login']);
    }
  }
  */

  
  /* savePassword(){

    console.log(this.form.value.confirmpassword);
    console.log(this.user.password);
    console.log(this.user.empId);
    if(this.user.password!=this.form.value.oldpassword){
      alert("Incorrect password");
      this.message = "Incorrect Old Password";
    }
    if(this.user.password==this.form.value.newpassword){
      alert("Password should not be same as old password");

    }
    if(this.form.value.newpassword != this.form.value.confirmpassword){
      alert("Enter Valid Password");
    }

    let userSave = JSON.stringify({
      id:this.user.id,
      empId: this.user.empId,
      oldPassword : this.form.value.oldpassword,
      newPassword:this.form.value.confirmpassword
    });
   


    this.service.savePassword(userSave).subscribe(
      data => {
        console.log(data);
      })
      this.router.navigate(['home']);
    } */


}
