import { Component, OnInit } from '@angular/core';
import { SkillService } from '../skill.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-recently-added-skills',
  templateUrl: './recently-added-skills.component.html',
  styleUrls: ['./recently-added-skills.component.css']
})
export class RecentlyAddedSkillsComponent implements OnInit {

  recentSkillList:any[]=[];
  recentSkillListLength : any;
  error:any;
  constructor(private skillService: SkillService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.skillService.getrecentSkill().subscribe(
      data => {
        this.recentSkillList = data;
        this.recentSkillListLength = this.recentSkillList.length;

        console.log("recent skill:::" + data)
        console.log("id recent skill: "+ data[0])

        
      },
      error => {
        this.error=error;
        console.log(this.error);
      }
      
    );
  }
  

  /* click(){

  this.router.navigate(['skills']);
} */

}
