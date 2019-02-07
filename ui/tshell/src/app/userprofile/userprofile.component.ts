import { Component, OnInit } from '@angular/core';
import { ViewprofileService } from '../viewprofile.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {


  assessmentsData: any[] = [];
  assessmentsForQn: any[] = [];
  userDetails: any = {
    id: 0,
    name: "",
    email: "",
    password: "",
    role: {
      id: 0,
      name: ""
    },
    employeeId: 0,
    image: null,
    skills: []
  };
  roles: any = [];
  changedRole: any;
  constructor(private viewprofileService: ViewprofileService, private service: AuthService) { }

  ngOnInit() {


  }

  searchById(searchid) {
    if (searchid != 0) {
      this.viewprofileService.getUserDetails(searchid).subscribe(
        data => {
          if (data != null) {
            this.userDetails = data;
          }
          else {
            this.userDetails = {
              id: -1,
              name: "",
              email: "",
              password: "",
              role: {
                id: 0,
                name: ""
              },
              employeeId: 0,
              image: null,
              skills: []
            };
            this.assessmentsData = [];
            this.assessmentsForQn = [];
          }
          this.viewprofileService.getRole().subscribe(
            data => {
              console.log(data);
              this.roles = data;
            }
          );
          console.log(data);
          this.viewprofileService.getUserAssessment(searchid).subscribe(
            data => {
              this.assessmentsForQn = data;
              this.assessmentsData = [];
              for (let i = 0; i < data.length; i++) {
                let skillPresent = false;
                for (let j = 0; j < this.assessmentsData.length; j++) {
                  if (this.assessmentsData[j].skillname == data[i].skill.name) {
                    skillPresent = true;
                    this.assessmentsData[j].assessments.push(data[i]);
                  }
                }
                if (!skillPresent) {
                  let skill = {
                    skillname: data[i].skill.name,
                    assessments: [data[i]],
                  }
                  this.assessmentsData.push(skill);
                }
              }
              console.log(this.assessmentsData);
              this.assessmentsData.forEach(element => {
                console.log(element);
              });

            });
        });

    }
  }
  imageUrl: string = "https://www.loopconnect.net/images/main/avatar.png";
  fileToUpload: File = null;

  handleFileInput(file: FileList) {
    this.fileToUpload = file.item(0);

    var reader = new FileReader();
    reader.onload = (event: any) => {
      this.imageUrl = event.target.result;
    }
    reader.readAsDataURL(this.fileToUpload);
  }
  changeRole(roleid) {
    console.log("Role id :=" + roleid);

    for (let role of this.roles) {
      if (role.id == roleid) {
        this.userDetails.role = role;
        let json = JSON.stringify(
          {
            employeeId: this.userDetails.employeeId,
            role: {
              id: role.id,
              name: role.name,
            }

          }
        );
        this.viewprofileService.updateUser(json).subscribe();

      }
    }
  }
}

