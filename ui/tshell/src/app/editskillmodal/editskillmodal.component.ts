import { Component, OnInit, Input, ChangeDetectorRef } from '@angular/core';
import { Topic } from '../topic';
import { FormGroup, FormControl, Validators, FormBuilder, FormArray } from '@angular/forms';
import { NgbActiveModal, NgbAlert } from '@ng-bootstrap/ng-bootstrap';
import { SkillserviceService } from '../skillservice.service';
import { ConfirmationDialogService } from '../confirmation-dialog.service';

@Component({
  selector: 'app-editskillmodal',
  templateUrl: './editskillmodal.component.html',
  styleUrls: ['./editskillmodal.component.css']
})
export class EditskillmodalComponent implements OnInit {
  status: number;
  error: any;
  expression: any;
  @Input() item: any;
  skills: any = [];
  topics: Array<Topic> = [];
  sametopic = false;
  emptyper = false;
  bigper = false;
  public imagePath;
  imgURL: any;
  public message: string;
  base64textString: string = '';

  addskillform = new FormGroup({
    id: new FormControl(0),
    name: new FormControl(
      '',
      [Validators.required,
      Validators.minLength(1),
      Validators.maxLength(25),
      Validators.pattern(/^[a-zA-Z0-9 ._-]+$/),

      ]),
    searchCount: new FormControl(0),
    active: new FormControl(false),
    testCount: new FormControl(0),
    description: new FormControl(
      '',
      [Validators.required,
      Validators.minLength(10),
      Validators.maxLength(400),
      ]),
    image: new FormControl,
    creationDate: new FormControl(new Date()),
    topicName: new FormControl(
      '',
      [
        Validators.minLength(2),
        Validators.maxLength(60),
        Validators.pattern(/^[a-zA-Z ._-]+$/),
      ]),
    topicPercentage: new FormControl(
      '',
      [
        Validators.minLength(1),
        Validators.maxLength(2),
      ]),
    topics: this.fb.array([])

  });

  // tslint:disable-next-line:max-line-length
  constructor(private cd: ChangeDetectorRef, public activeModal: NgbActiveModal, private skillService: SkillserviceService, private fb: FormBuilder, private confirmationDialogService: ConfirmationDialogService) { }
  addTopic(id, topicname, percentage) {
    const topic1 = new Topic(id, topicname, percentage);
    let counter = 0;
    let percounter = 0;
    let morethahun = 0;
    if (topicname === '') {
      counter = 1;
    }

    if (percentage === '') {
      percounter = 1;
      this.emptyper = true;
      this.bigper = false;
    }
    if (percentage > 100) {
      this.bigper = true;
      this.emptyper = false;
      morethahun = 1;
    }
    this.topics.forEach(element => {
      if (topicname === element.name || topicname === '') {
        counter = 1;
      }
    });

    if (counter === 0 && percounter === 0 && morethahun === 0) {
      this.topics.push(topic1);
      this.sametopic = false;
      this.emptyper = false;
      this.bigper = false;
      this.clearInput();
    }

    if (counter !== 0) {
      this.sametopic = true;
    }

    if (counter === 0 && percounter !== 0) {
      this.sametopic = false;
    } else if (counter !== 0 && percounter === 0) {
      this.emptyper = false;
    }

  }
  removeTopic(topic) {
    const index = this.topics.indexOf(topic);
    this.confirmationDialogService.confirm(`Deletion of "${this.topics[index].name}"`, 'Do you really want to Delete ?')
      .then((confirmed) => {
        if (confirmed) {
          console.log('User confirmed:', confirmed);
          this.skillService.deleteTopic(this.topics[index].name, this.item.id).subscribe();
          this.topics.splice(index, 1);
        } else {
          console.log('User confirmed:', confirmed);
          return;
        }
      })
      .catch(() => console.log('User dismissed the dialog (e.g., by using ESC, clicking the cross icon, or clicking outside the dialog)'));
  }

  get topicName(): any { return this.addskillform.get('topicName'); }
  get topicPercentage(): any {
    return this.addskillform.get('topicPercentage');
  }
  clearInput() { this.topicName.reset(); this.topicPercentage.reset();}
  clearAllInput() {
    this.topics = [];
  }

  submitSkill() {
    const control = <FormArray>this.addskillform.controls['topics'];
    this.topics.forEach(element => {
      control.push(new FormControl(element));
    });
    //to avoid circular JSON Structure
    const getCircularReplacer = () => {
      const seen = new WeakSet();
      return (key, value) => {
        if (typeof value === "object" && value !== null) {
          if (seen.has(value)) {
            return;
          }
          seen.add(value);
        }
        return value;
      };
    };
    if (this.addskillform.controls['creationDate'].value == null) {
      this.addskillform.controls['creationDate'].patchValue(new Date());
    }

    this.skillService.updateSkill(JSON.stringify(this.addskillform.value, getCircularReplacer())).subscribe(
      data => {
        this.status = data;
        this.error = false;
        if (this.status === 2) {
          this.addskillform.reset();
          this.clearAllInput();
        }
      }, error => {
        this.error = error;
        this.status = 0;
      });
  }

  preview(files) {
    if (files.length === 0) {
      return;
    }
    const mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.message = "Only images are supported.";
      return;
    }
    const reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]);
    reader.onload = (_event) => {
      // this.addskillform.controls['image'].patchValue(
      //   reader.result
      // );
      this.cd.markForCheck();
      this.imgURL = reader.result;
    };
  }

  // ByteTobase64(base64) {
  //   return function (buffer) {
  //     let binary = '';
  //     let bytes = new Uint8Array(buffer);
  //     let len = bytes.byteLength;
  //     for (var i = 0; i < len; i++) {
  //       binary += String.fromCharCode(bytes[i]);
  //     }
  //     return window.btoa(binary);
  //   };
  // }

  ngOnInit() {
    this.addskillform.patchValue(this.item);
    const control = <FormArray>this.addskillform.controls['topics'];
    this.item.topics.forEach(element => {
      this.addTopic(element.id, element.name, element.percentage);
    });
  }

}
