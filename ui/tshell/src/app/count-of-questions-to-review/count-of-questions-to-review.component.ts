import { Component, OnInit } from '@angular/core';
import { CountOfPendingQuestionsService } from '../count-of-pending-questions.service';

@Component({
  selector: 'app-count-of-questions-to-review',
  templateUrl: './count-of-questions-to-review.component.html',
  styleUrls: ['./count-of-questions-to-review.component.css']
})
export class CountOfQuestionsToReviewComponent implements OnInit {
  countList: any;
  skillList: any;
  show: boolean = false;
  showAddButton:boolean = false;
  skillId: any;
  skillName : any;
  name:any;
  previousSelectedSkill: any;
  constructor(private countPendingService: CountOfPendingQuestionsService) { }

  ngOnInit() {
    this.countPendingService.CountReviewQ().subscribe(
      data => {
        this.countList = data;
      })
  }

  pressSkill(name) {
    this.show = false;
    if(name.length>0){
    this.countPendingService.skillsOnSearch(name).subscribe(
      data => {
        this.skillList = data;
      });
    }
    if (name == this.previousSelectedSkill) {
      this.currentSkill(name);
    }
  }

  currentSkill(name) {
    this.showAddButton = false;
    this.previousSelectedSkill = "";
    for(let skillOfNameAndId of this.skillList){
      if(skillOfNameAndId[0]==name){
        this.skillId = skillOfNameAndId[1];
        this.skillName = skillOfNameAndId[0];
        this.countPendingService.skillId=this.skillId;
       this.countPendingService.skillName=this.skillName;
        this.show = true;
      }
    }
    this.previousSelectedSkill = name;
  }

  showAddbtn(){
    this.showAddButton = true;
  }
  disableAddbtn(){
    this.showAddButton = false;
  }
}
