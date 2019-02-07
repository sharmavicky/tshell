import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AppinfoService } from '../appinfo.service';
import { NumberFormatterPipe } from '../numberFormatterPipe';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers:[NumberFormatterPipe]
})
export class DashboardComponent implements OnInit {

  skillCount:number;
  assessmentCount:number;
  userCount:number;
  questionCount:number;
  error:any;
  
  constructor(private route: ActivatedRoute, private router: Router,
               private appinfoService: AppinfoService) { }

  ngOnInit() {
    this.appinfoService.getSkillCount().subscribe(
      data =>{
        this.skillCount = data;
        console.log(data)
      },
      error => {
        this.error=error;
        console.log(this.error);
      }
    );

    this.appinfoService.getUserCount().subscribe(
      data =>{
        this.userCount = data;
        console.log(data)
      },
      error => {
        this.error=error;
        console.log(this.error);
      }
    );

    this.appinfoService.getQuestionCount().subscribe(
      data =>{
        this.questionCount = data;
        console.log(data)
      },
      error => {
        this.error=error;
        console.log(this.error);
      }
    );

    this.appinfoService.getAssessmentCount().subscribe(
      data =>{
        this.assessmentCount = data;
        console.log(data)
      },
      error => {
        this.error=error;
        console.log(this.error);
      }
    );
  }

}
