import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { AuthService } from '../auth.service';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { ChangepasswordService } from '../changepassword.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  status = {
    currentPasswordIncorrect: false,
    passwordChanged: false,
    newAndOldPasswordSame: false,
    message: ''
  };


  message: any;
  employeeId: any;
  password: any;
  newPassword: any;
  currentPassword: any;
  error = false;


  /* **************************************** Validations **************************************** */

  form = new FormGroup({
    oldPassword: new FormControl(
      '',
      [Validators.required,
      Validators.minLength(6),
      Validators.maxLength(30),
      ]),

    newPassword: new FormControl(
      '',
      [Validators.required,
      Validators.minLength(6),
      Validators.maxLength(30),
      ]),


    confirmPassword: new FormControl(
      '',
      [Validators.required,
      Validators.minLength(6),
      Validators.maxLength(30),
      ]),
  });

  constructor(public service: AuthService, private router: Router, private passwordService: ChangepasswordService) { }

  ngOnInit() {
  }

  /* ********************************* modal closed with status & error ******************************* */

  modalClose() {
    this.form.reset();
    this.error = false;
    this.status.message = '';
    this.status.currentPasswordIncorrect = false,
      this.status.passwordChanged = false,
      this.status.newAndOldPasswordSame = false

  }

  /* *********************************** Save the New Password *************************************** */

  savePassword() {
    console.log("inside the savePassword()");

    this.currentPassword = this.form.value.oldPassword,
      this.newPassword = this.form.value.confirmPassword

    this.passwordService.savepassword(this.service.getEmployeeId(), this.currentPassword, this.newPassword).subscribe(
      data => {
        console.log("inside ts password");
        console.log("backen data " + data);
        this.status = data;

        /* ************************ To Reset The Form Details ************************ */

        this.form.reset();

        /* **************************************************************************** */
      },
      error => {
        this.error = true;
      }

    );

  }
  clickedOnModal() {
    this.error = false;
  }
  openModal() {
    this.form.reset();
    this.error = false;
    this.status.message = '';
    this.status.currentPasswordIncorrect = false,
      this.status.passwordChanged = false,
      this.status.newAndOldPasswordSame = false
  }
}










