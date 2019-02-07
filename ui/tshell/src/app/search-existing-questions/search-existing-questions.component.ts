import { Component, OnInit } from '@angular/core';
import { SearchExistingQuestionsService } from '../search-existing-questions.service';
import { FormBuilder, Validators, FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { elementAt } from 'rxjs/operators';

@Component({
  selector: 'app-search-existing-questions',
  templateUrl: './search-existing-questions.component.html',
  styleUrls: ['./search-existing-questions.component.css']
})
export class SearchExistingQuestionsComponent implements OnInit {

  constructor(private service: SearchExistingQuestionsService, private route: ActivatedRoute, private fb: FormBuilder) { }

  ngOnInit() {
    this.route.params.subscribe(
      params => {
        this.skillId = params['id'];
        this.skillName = params['name'];
      }
    )
    this.service.fetchReviewQuestion(this.skillId).subscribe(
      data => {
        if (data[0] != null) {
          this.question = data[0];
          this.questionId = this.question.id;
          this.optionList = this.question.optionList;
          this.countOption = this.optionList.length;
          this.message = 'undefined';
        } else {
          this.message = 'fail';
        }
      }, error => {
        this.message = 'fail';
      }
    )
  }

  form = new FormGroup({
    description: new FormControl(
      '',
      [Validators.required, Validators.maxLength(250)])
  })

  questionForm = new FormGroup({
    id: new FormControl(),
    question: new FormControl('', [Validators.required, Validators.maxLength(500)])
  })


  countOption: number;
  error: any;
  status = false;
  skillId: number;
  question: any = {};
  optionList: any;
  questionId: number;
  optionDescription: string = '';
  skillName: string;
  message: string;
  selectedOptionId: number;
  deleteOptionStatus: string = 'undefined';
  searchedQuestionsList: any = '';
  queUpdateStatus: string = '';
  updateOptionStatus: string = '';
  editOptionStatus: string = '';

  addOption() {
    let newOption = {
      answer: false,
      description: this.form.value.description,
      question: {
        id: this.questionId
      }
    };
    this.service.addOption(JSON.stringify(newOption)).subscribe(
      data => {
        if (data != null) {
          this.status = true;
          this.optionDescription = '';
          this.question = data;
          this.questionId = this.question.id;
          this.optionList = this.question.optionList;
          this.countOption = this.optionList.length
        } else {
          this.message = 'fail';
        }
      },
      error => {
        this.error = error;
        this.status = false;
      }
    )
    this.form.reset();
  }

  modalClose() {
    this.status = false;
    this.error = false;
    this.form.reset();
    this.questionForm.reset();
    this.queUpdateStatus = '';
    this.editOptionStatus = '';
  }

  approveQuestion(questionId: number) {
    let status: string = 'approve';
    this.service.updateQuestionStatus(questionId, status, this.skillId).subscribe(
      data => {
        if (data[0] != null) {
          this.question = data[0];
          this.questionId = this.question.id;
          this.optionList = this.question.optionList;
          this.countOption = this.optionList.length;
        } else {
          this.message = 'fail';
        }
      }
    )
  }

  rejectQuestion(questionId: number) {
    let status: string = 'reject';
    this.service.updateQuestionStatus(questionId, status, this.skillId).subscribe(
      data => {
        if (data[0] != null) {
          this.question = data[0];
          this.questionId = this.question.id;
          this.optionList = this.question.optionList;
          this.countOption = this.optionList.length;
        } else {
          this.message = 'fail';
        }
      }
    )
  }

  getSelectedOption(optionId: number) {
    this.selectedOptionId = optionId;
    this.deleteOptionStatus = 'undefined';
    this.editOptionStatus = 'undefined';
    this.optionList.forEach(
      element => {
        if (element.id == optionId) {
          this.form.patchValue({ description: element.description });
        }
      });
  }

  deleteOption() {
    this.service.deleteOption(this.selectedOptionId).subscribe(
      data => {
        if (data) {
          this.deleteOptionStatus = 'true';
          this.optionList.forEach(
            element => {
              if (element.id == this.selectedOptionId) {
                this.optionList.splice(this.optionList.indexOf(element), 1);
              }
            });
          this.countOption = this.optionList.length;
        } else {
          this.deleteOptionStatus = 'false';
        }
      }, error => {
        this.deleteOptionStatus = 'false';
      });
  }

  saveOption() {
    let option = JSON.stringify({
      id: this.selectedOptionId,
      description: this.form.value.description,
    });
    this.service.saveoption(option).subscribe(
      data => {
        if (data) {
          this.editOptionStatus = 'true';
          this.optionList.forEach(
            element => {
              if (element.id == this.selectedOptionId) {
                element.description = this.form.value.description;
              }
            });
        } else {
          this.editOptionStatus = 'false';
        }
      },
      error => {
        this.editOptionStatus = 'false';
      });
  }

  searchQ(searchquestion) {
    let json = JSON.stringify(
      {
        keyword: searchquestion
      }
    );
    this.service.searchedQuestions(json).subscribe(data => {
      if (data[0] != null) {
        this.searchedQuestionsList = data;
      } else {
        this.searchedQuestionsList = null;
      }
    },
      error => {
        this.searchedQuestionsList = null;
      });
  }


  setQuestionForm() {
    this.queUpdateStatus = '';
    this.questionForm.patchValue({ id: this.question.id });
    this.questionForm.patchValue({ question: this.question.question });
  }

  editQuestion() {
    this.service.updateQuestion(this.questionForm.value).subscribe(
      data => {
        if (data != null) {
          this.queUpdateStatus = 'true';
          this.question = data;
          this.questionId = this.question.id;
        } else {
          this.queUpdateStatus = 'false';
        }
      }, error => {
        this.queUpdateStatus = 'false';
      }
    )
  }

  changeOptionStatus(optionId: number) {
    this.updateOptionStatus = '';
    this.service.modifyOptionStatus(optionId).subscribe(
      data => {
        if (data) {
          this.optionList.forEach(
            element => {
              if (element.id == optionId) {
                if (element.answer) {
                  element.answer = false;
                } else {
                  element.answer = true;
                }
              }
            });
          this.updateOptionStatus = 'true';
        } else {
          this.updateOptionStatus = 'false';
        }
      }, error => {
        this.updateOptionStatus = 'false';
      }
    )
  }
}
