import { Component, OnInit } from '@angular/core';
import { ViewprofileService } from '../viewprofile.service';
import { AuthService } from '../auth.service';
import { FormGroup,FormControl,Validators } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { Userskill } from '../userskill';

@Component({
  selector: 'app-viewprofile',
  templateUrl: './viewprofile.component.html',
  styleUrls: ['./viewprofile.component.css']
})
export class ViewprofileComponent implements OnInit {

  user: any;
  userDetails: any = {};
  assessmentsData: any[] = [];
  assessmentsForQn:any[]=[];
  emailPattern = "^[a-zA-Z0-9._%+-]+@[A-Za-z0-9.-]+\.[a-zA-Z]{3,5}$";
  updateStatus: any;
  skillId : any;
  skillName :any;
  skillIsSelected:any;
  skillList:any;
  previousSelectedSkill:any;
  userskills:Userskill[]=[];
  emptyuserskills:Userskill[]=[];
 dbstatus=false;
 arraystatus=false;
 emptyinput=false;
// inputfieldname:any;
name:any;
isAddButtonClicked:any;
arraycounter:any;
 
  constructor(private viewprofileService: ViewprofileService, private service: AuthService,private fb: FormBuilder) { }
  form1 :any;
  ngOnInit() {

    this.updateStatus = false;
    this.form1 = new FormGroup({
      employeeId: new FormControl(''),
  
      name: new FormControl('',[
        , Validators.required,
         Validators.maxLength(45)
       ]),
  
      email: new FormControl('',[
        , Validators.required,
        Validators.pattern(this.emailPattern)]),
        
      
    });
   
    
    if (this.service.employeeId != '') {
      this.viewprofileService.getUserDetails(this.service.employeeId).subscribe(
        data => {
          this.userDetails = data;
          let json =({
            employeeId:this.userDetails.employeeId,
            name:this.userDetails.name,
            email:this.userDetails.email,
            
          });
          console.log("json data : = "+ json);
          this.form1.patchValue(json);
          console.log("form control data set : = "+this.form1.value);

          console.log(data);

        });
      this.viewprofileService.getUserAssessment(this.service.employeeId).subscribe(
        data => {
          this.assessmentsForQn=data;
          for(let i=0;i<data.length;i++){
            let skillPresent=false;
            for(let j=0;j<this.assessmentsData.length;j++){
              if(this.assessmentsData[j].skillname==data[i].skill.name){
                skillPresent=true;
                this.assessmentsData[j].assessments.push(data[i]);
              }
            }
            if(!skillPresent){
              let skill={
                skillname:data[i].skill.name,
                assessments:[data[i]],                
              }
              this.assessmentsData.push(skill);
            }
          }          
          console.log(this.assessmentsData);
          this.assessmentsData.forEach(element => {
            console.log(element);
          });

        });

      
    }
  }

  save(){
    console.log("1st Status"+this.updateStatus);
    console.log(this.form1.value);
    this.viewprofileService.save(this.form1.value).subscribe(
      data => {
        console.log(data);
        this.viewprofileService.getUserDetails(this.service.employeeId).subscribe(
          data => {
            this.userDetails = data;
  
            console.log(data);
             this.updateStatus = true;
            
          });
      }
    );
    
   }

  imageUrl: string = "https://www.loopconnect.net/images/main/avatar.png";
  fileToUpload: File = null;

  handleFileInput(file: FileList) {
    this.fileToUpload = file.item(0);

    var reader = new FileReader();
    reader.onload = (event: any) => {
      this.imageUrl = event.target.result;

    }
    let by=reader.readAsDataURL(this.fileToUpload);


  }

  modalOpen(){
    this.updateStatus=false;
    this.updateStatus='';
    this.dbstatus=false;
        this.arraystatus=false;
        this.emptyinput=false;
        this.name='';
        this.isAddButtonClicked=false;
        this.arraycounter=0;
        this.userskills=[];

  }

  pressSkill(name) {
    name=this.name;
    if(name.length>0){
    this.viewprofileService.skillsOnSearch(name).subscribe(
      data => {
        
        this.skillList = data;
      });
    }
 }

  currentSkill(name) {
    name=this.name;
    for(let skillOfNameAndId of this.skillList){
      if(skillOfNameAndId[0]==name){
        this.skillId = skillOfNameAndId[1];
        this.skillName = skillOfNameAndId[0];
      }
    }
  }

  

  skilladded(skillasd){
    this.isAddButtonClicked=true;
  /*   if(!skillasd[2]){ */
    this.skillId = skillasd[1];
    this.skillName = skillasd[0];
    this.arraycounter=0;
    let dbcounter=0;
    for(let i=0; i< this.userDetails.skills.length;i++){
      if(this.userDetails.skills[i].id==this.skillId){
        dbcounter=1;
      }
    }

    for(let i=0; i<  this.userskills.length;i++){
      if(this.userskills[i].id==this.skillId){
        this.arraycounter=1;
      }

    }
      console.log("INCOMING SKILLLLLL" + skillasd);
   
    this.previousSelectedSkill = skillasd[0];
    if(this.arraycounter==0 && dbcounter==0){
    const userSkill = new Userskill(this.skillId, this.skillName);
    console.log("UserSkill: id-"  + userSkill.id+";name-"+userSkill.name);
    
    this.userskills.push(userSkill);
    this.dbstatus=false;
    this.arraystatus=false;
    this.emptyinput=true;
  }

  if(this.arraycounter==0 && dbcounter!=0){
  this.dbstatus=true;
  this.arraystatus=false;

  }  if(this.arraycounter!=0 && dbcounter==0){

    this.dbstatus=false;
    this.arraystatus=true;

  }
    /* } else {
      console.log("already exists");
    } */
    
   //console.log("total SKILLLLLL List " + this.skillList);
     /* for(let skillOfNameAndId of this.skillList){
     
        this.skillId = skillOfNameAndId[1];
        this.skillName = skillOfNameAndId[0];
        console.log("Skill name: "  +   this.skillName);
        console.log("Skill id: "  +   this.skillId);
        const userSkill = new Userskill(this.skillId, this.skillName);
        console.log("UserSkill: "  + userSkill);
        this.userskills.push(userSkill);

        console.log("Skill"  +   this.userskills);

       
       
    } */
    
     
  }

  
  clearAllInput() {
    this.userskills = [];
  }

  removeSkill(skill) {
    let index = this.userskills.indexOf(skill);
    this.userskills.splice(index, 1);
  }
  saveskill(){
    console.log(this.userskills.length);
    if(this.userskills.length>0){
    for(let skill of this.userskills){
    this.userDetails.skills.push(skill);
    console.log(this.userDetails);
    }
    this.viewprofileService.saveskills(this.userDetails).subscribe(
      data =>{
        this.clearAllInput();
        this.dbstatus=false;
        this.arraystatus=false;
        this.emptyinput=false;
   /*      $("#myModal").modal("hide"); */
        console.log("after saving skills");
      });
    }else{
      //this.emptyinput=true;

    }


  }
  
}