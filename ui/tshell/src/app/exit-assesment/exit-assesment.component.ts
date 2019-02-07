import { Component, OnInit } from '@angular/core';
import { ExitAssesmentService } from '../exit-assesment.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';


// declare var countdown: any;

@Component({
  selector: 'app-exit-assesment',
  templateUrl: './exit-assesment.component.html',
  styleUrls: ['./exit-assesment.component.css'],
})
export class ExitAssesmentComponent implements OnInit {
  skillId: number;
  skillName: string;
  assessmentType: string;
  assessmentDuration = 1200;   // in secs i.e.1200 = 20 mins
  questionIds: any;
  questionId: number;
  error: any;
  question: any;
  QuesJson: any;
  employeeId: string;

  startAssesmentJson: any;
  assesmentDetails: any;

  questionSet: any = [];
  questionResponse: any = [];
  assessmentOptions: any = [];

  optionList: any = [];

  answerStatus: any = [];
  mode = 'quiz';

  presentStatus: any = [];
  pager = {
    index: 0,
    size: 1,
    count: 1
  };
  timer: any = null;
  startTime: Date;
  endTime: Date;
  ellapsedTime = '00:00';
  duration = '';

  // tslint:disable-next-line:max-line-length
  constructor(private router: Router, private assesmentService: ExitAssesmentService, private route: ActivatedRoute, private authService: AuthService) { }

  ngOnInit() {
    this.employeeId = this.authService.getEmployeeId();
    this.route.params.subscribe(params => {
      this.skillId = params['skillid'];
      this.skillName = params['skillname'];
      this.assessmentType = params['type'];
    });
    // JSON FOR starting Assessment and creating entry in backend
    this.startAssesmentJson = JSON.stringify({
      date: Date.now(),
      type: this.assessmentType,
      skill: {
        id: this.skillId
      },
      user: {
        employeeId: this.employeeId
      }
    });
    console.log(this.startAssesmentJson);

    // Retriveing the question Ids for the assessment based on skill Id.
    this.assesmentService.getQuestionId(this.skillId).subscribe(data => {
      this.questionIds = data;
      for (let i = 1; i <= this.questionIds.length; i++) {
        this.questionSet.push(i);
        this.presentStatus.push(i);
        this.answerStatus.push(0);
      }
      console.log(this.questionIds);
      this.questionId = this.questionIds[this.pager.index];
      // console.log(this.questionId);

      // Creating Assessment entry in backend
      this.assesmentService.startAssessment(this.startAssesmentJson).subscribe(d => {
        this.assesmentDetails = d;
        //  console.log(this.assesmentDetails);
      },
        error => {
          console.log("Error When creating entry for assessment");
          this.error = error;
        });
      this.assesmentService.getQuestion(this.questionId).subscribe(q => {
        this.question = q;
        this.loadQuiz();
        this.loadQuestions(this.question, this.pager.index);
        // console.log(this.question);
      });

    },
      error => {
        console.log("Error Fetching Questions ids");
        this.error = error;
      });
  }


  loadQuestions(json, index: number) {
    if (this.presentStatus[index] != 'visited') {
      this.questionSet[index] = json[0];
      this.presentStatus[index] = 'visited';
    }
    // console.log(this.questionSet);
  }

  loadQuiz() {
    this.pager.count = this.questionIds.length; // Total No of Questions
    this.startTime = new Date();
    this.timer = setInterval(() => { this.tick(); }, 1000);
    this.duration = this.parseTime(this.assessmentDuration);
    this.mode = 'quiz';
  }

  tick() {
    const now = new Date();
    const diff = (now.getTime() - this.startTime.getTime()) / 1000;
    if (diff >= this.assessmentDuration) {
      this.onSubmit();
    }
    this.ellapsedTime = this.parseTime(diff);
  }

  parseTime(totalSeconds: number) {
    let mins: string | number = Math.floor(totalSeconds / 60);
    let secs: string | number = Math.round(totalSeconds % 60);
    mins = (mins < 10 ? '0' : '') + mins;
    secs = (secs < 10 ? '0' : '') + secs;
    return `${mins}:${secs}`;
  }



  get filteredQuestions() {
    return this.questionSet ? this.questionSet.slice(this.pager.index, this.pager.index + this.pager.size) : [];
  }

  onSelect(question: any, option: any) {
    // console.log(question);  console.log(option);
    // AnswerType == 1 means radio button  and only one option is true
    if (question.questionAnswerType.id == 1) {
      question.optionList.forEach((x) => {
        if (x.id == option.id) {
          x.response = true;
          // console.log('Option : ' + option.id + ', x.id : ' + x.id + ', response : ' + x.response);
        } else {
          x.response = false;
          // console.log('Option : ' + option.id + ', x.id : ' + x.id + ', response : ' + x.response);
        }
      });
    }
    // AnswerType == 2 means checkbox  button  and only multiple option are true
    if (question.questionAnswerType.id == 2) {
      question.optionList.forEach((x) => {
        if (x.id == option.id) {
          if (x.counter % 2 == 0) {
            // When user checks counter = even number and marked as Checked
            x.response = true;
            x.counter++;
            // console.log('Option : ' + option.id + ', x.id : ' + x.id + ', response : ' + x.response);
          } else {
            // When user unchecks counter = odd number and marked as Checked
            x.response = false;
            this.answerStatus[this.pager.index] = 0;
            x.counter++;
            // console.log('Option : ' + option.id + ', x.id : ' + x.id + ', response : ' + x.response);
          }

        }
      });
    }
    // tslint:disable-next-line:max-line-length
    question.optionList.every(e => e.response == false) ? this.answerStatus[this.pager.index] = 0 : this.answerStatus[this.pager.index] = 1;
    this.questionResponse = question;
  }

  correctanswer(index) {
    return this.answerStatus[index] ? 'Answered' : 'Not Answered';
  }


  // Next Previous and Quest Pallette Buttons
  goTo(index: number) {
    if (index >= 0 && index < this.pager.count) {
      this.pager.index = index;
      this.questionId = this.questionIds[this.pager.index];
      // console.log(this.questionId);
      this.assesmentService.getQuestion(this.questionId).subscribe(d => {
        this.question = d;
        this.loadQuestions(this.question, this.pager.index);
        // console.log(this.question);
        this.saveResponse();
      });
    }
  }

  saveResponse() {
    if (this.questionResponse.length != 0) {
      this.questionResponse.optionList.forEach(o => {
        this.assessmentOptions.push({
          id: o.id,
          description: o.description,
          answer: o.answer
        }
        );
      });
      this.QuesJson = JSON.stringify({
        assessment: this.assesmentDetails,
        question: this.questionResponse,
        correct: 'false',
        assessmentQuestionOption: this.assessmentOptions,
      });
      // console.log(this.questionResponse);
      // console.log(this.QuesJson);
      this.assesmentService.saveAssessmentResponse(this.QuesJson).subscribe();
      this.assessmentOptions = [];
      this.questionResponse = [];
    }
  }

  onSubmit() {
    this.saveResponse();
    this.assesmentDetails['endTime'] = Date.now();
    console.log('saving');
    this.assesmentService.submitAssessment(this.assesmentDetails).subscribe();
    clearTimeout(this.timer);
    this.router.navigate(['/assesmentscore', this.skillName, this.assesmentDetails.id]);

  }

}
