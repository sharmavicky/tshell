import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-learner-homepage',
  templateUrl: './learner-homepage.component.html',
  styleUrls: ['./learner-homepage.component.css']
})
export class LearnerHomepageComponent implements OnInit {

  constructor(public service: AuthService) { }

  ngOnInit() {
  }

}
