import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-admin-signup',
  templateUrl: './admin-signup.component.html',
  styleUrls: ['./admin-signup.component.css']
})
export class AdminSignupComponent implements OnInit {

  constructor(private router: Router, public service: AuthService) { }

  ngOnInit() {
  }

  sign() {
    this.service.setisDefaultAdmin(true);
    this.router.navigate(['/signup']);
  }

}
