import { Component, OnInit } from '@angular/core';
import { Router, Routes } from '@angular/router';
import { AssessmenthistoryService } from '../assessmenthistory.service';

@Component({
  selector: 'app-assessmenthistory',
  templateUrl: './assessmenthistory.component.html',
  styleUrls: ['./assessmenthistory.component.css']
})
export class AssessmenthistoryComponent implements OnInit {

  constructor(/*private router: Router, public history : AssessmenthistoryService*/) { }

  ngOnInit() {
  }
 

}
/*import { Component, OnInit } from '@angular/core';
import { Router, Routes } from '../../../node_modules/@angular/router';
import { FlightserviceService } from '../flightservice.service';
import { SummaryResolver } from '@angular/compiler';
import { SummaryService } from '../summary.service';
@Component({
  selector: 'app-flight-detail',
  templateUrl: './flight-detail.component.html',
  styleUrls: ['./flight-detail.component.css']
})
export class FlightDetailComponent implements OnInit {  

  constructor(private router: Router, public flightService: FlightserviceService,private summaryService:SummaryService) { }

  ngOnInit() {    
        
  }
  book(){
    this.router.navigate(['login']);
  }
}
*/