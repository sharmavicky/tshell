import { Component, OnInit, } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ExitAssesmentService } from '../exit-assesment.service';

@Component({
  selector: 'app-instruction',
  templateUrl: './instruction.component.html',
  styleUrls: ['./instruction.component.css']
})
export class InstructionComponent implements OnInit {
  questionId: any;
  result: any;
  taken: any;
  len: any;
  samplearray: any = [];
  skillId: number;
  skillName: string;
  assessmentType: string;
  // tslint:disable-next-line:max-line-length

  // tslint:disable-next-line:max-line-length
  constructor(private router: Router, private service: ExitAssesmentService, private route: ActivatedRoute) { }
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.skillId = params['skillid'];
      this.skillName = params['skillname'];
      this.assessmentType = params['type'];
    });

  }

  startAssesment() {
    this.router.navigate(['/assesment', this.skillId, this.skillName, this.assessmentType]);
  }
}
