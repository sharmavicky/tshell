import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ContributeQuestionService } from '../contribute-question.service';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { ConfirmationDialogService } from '../confirmation-dialog.service';
import { CountOfPendingQuestionsService } from '../count-of-pending-questions.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-preview-questions',
  templateUrl: './preview-questions.component.html',
  styleUrls: ['./preview-questions.component.css']
})
export class PreviewQuestionsComponent implements OnInit {
  questionsList: any;
  csvData: any = null;
  errorCount: any;
  empId: any;
  constructor(private dialogService: ConfirmationDialogService, private authService: AuthService, private router: Router, private contributeQuestionService: ContributeQuestionService) { }

  ngOnInit() {
    this.empId = this.authService.getEmployeeId();
    this.csvData = this.contributeQuestionService.getCsvData();
    this.errorCount = 0;
    if (this.csvData != null) {
      for (let i = 0; i < this.csvData.length; i++) {
        if (this.csvData[i].error != null || !this.csvData[i].validTopic) {
          this.errorCount += 1;
        }
      }
      console.log(this.errorCount);
    }
  }

  submitForReview() {
    this.dialogService.confirm(`Are you sure?`, `Questions are submitted successfully for Review!`)
      .then((confirmed) => {
        if (confirmed) {
          console.log('submit() is called');
          this.contributeQuestionService.submitForReview(this.empId).subscribe(
            data => {
            }
          );
          this.router.navigate(['/addquestion']);
        } else {
          this.router.navigate(['/preview']);
        }
      })
  }
  approveSubmittted() {
    this.dialogService.confirm(`Are you sure?`, `Questions saved successfully as Approved!`)
      .then((confirmed) => {
        if (confirmed) {
          console.log('approved() is called');
          this.contributeQuestionService.approveSubmittted(this.empId).subscribe(
            data => {
            }
          );
          this.router.navigate(['/addquestion']);
        } else {
          this.router.navigate(['/preview']);
        }
      })
  }
  retry() {
    this.dialogService.alert(`Are you sure?`,
      `No questions will be saved!`);
    this.router.navigate(['/addquestion']);
  }
}
