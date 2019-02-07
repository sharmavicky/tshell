import { Component, OnInit, Input } from '@angular/core';
import { SkillmodalComponent } from '../skillmodal/skillmodal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute } from '@angular/router';
import { SkillService } from '../skill.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-skillpage',
  templateUrl: './skillpage.component.html',
  styleUrls: ['./skillpage.component.css']
})
export class SkillpageComponent implements OnInit {
  active: any;
  skillName: any;
  skills: any;
  Role: any;
  showEdit = false;
  showActive = false;
  showAddskill = false;
  userLoggedInn = false;
  showEdittopic = false;
  toppers: any;
  // tslint:disable-next-line:max-line-length
  constructor(private modalService: NgbModal, private authService: AuthService, private route: ActivatedRoute, private skillService: SkillService) { }

  ngOnInit() {
    this.skillName = this.route.snapshot.paramMap.get('name');

    console.log("name from url :" + this.skillName);
    this.skillService.getSkillbyName(this.skillName).subscribe(data => {
      this.skills = data;
      console.log(this.skills);

      this.skillService.getSkillTopper(this.skills.id).subscribe(data1 => {
        this.toppers = data1;
      });

    });



    console.log("Role of user12: " + this.authService.role);
    this.Role = this.authService.role;
    this.userLoggedInn = this.authService.loggedIn;
    console.log("is he logged in:  " + this.userLoggedInn);

    if (this.Role === undefined) {
      this.showEdit = false;
      this.showActive = false;
      this.showAddskill = false;

    } else {
      if (this.Role.toUpperCase() === "Learner".toUpperCase()) {
        this.showEdit = false;
        this.showActive = false;
        this.showAddskill = false;
      }
      if (this.Role.toUpperCase() === "admin".toUpperCase()) {
        this.showEdit = true;
        this.showActive = true;
        this.showAddskill = true;
      }
      if (this.Role.toUpperCase() === "sme".toUpperCase()) {
        this.showEdit = true;
        this.showActive = false;
        this.showAddskill = true;
      }
    }

  }
  handleFileInput(file: FileList) {
    const fileToUpload = file.item(0);

    const reader = new FileReader();
    reader.onload = (event: any) => {
      const imageUrl = event.target.result;
    };
    reader.readAsDataURL(fileToUpload);
  }




}
