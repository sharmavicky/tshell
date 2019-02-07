import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl, FormBuilder, FormArray } from '@angular/forms';
import { Skill, ReferenceSkill } from '../skill';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map, delay } from 'rxjs/operators';
import { SkillserviceService } from '../skillservice.service';
import { ConfirmationDialogService } from '../confirmation-dialog.service';
import { error } from '@angular/compiler/src/util';

@Component({
  selector: 'app-reference-skill-model',
  templateUrl: './reference-skill-model.component.html',
  styleUrls: ['./reference-skill-model.component.css']
})
export class ReferenceSkillModelComponent implements OnInit {

  @Input() allReferenceSkills: any;
  @Input() allSkills: any;
  @Input() skill: any;
  @Input() type: any;
  model: any;
  sameSkill = false;

  addReferenceSkillForm = new FormGroup({
    id: new FormControl(),
    skill: new FormControl(this.skill),
    classifier: new FormControl(),
    referenceSkill: new FormControl(),
  });

  referenceSkillName: Array<ReferenceSkill> = [];

  // tslint:disable-next-line:max-line-length
  constructor(public activeModal: NgbActiveModal, private fb: FormBuilder, private skillService: SkillserviceService, private confirmationDialogService: ConfirmationDialogService) { }

  ngOnInit() {
    this.allReferenceSkills.forEach(element => {
      this.addReferenceSkill(element.id, element.skill, element.referenceSkill, element.classifier);
    });
  }

  formatter = (x: { name: string }) => "";
  search = (text$: Observable<string>) => text$.pipe(
    debounceTime(200),
    distinctUntilChanged(),
    map(term => (term === '' ? []
      // : this.allSkills.filter(v => v.name.toLowerCase().indexOf(term.toLowerCase()) > -1)
      : this.allSkills.filter(v => new RegExp(term, 'gi').test(v.name)).slice(0, 5)
    ))
  )

  itemSelected($event) {
    let counter = 0;
    this.referenceSkillName.forEach(element => {
      if ($event.item.name === element.referenceSkill.name) {
        this.sameSkill = true;
        counter = 1;
      }
    });
    if (counter === 0) {
      if (this.type === 1) {
        this.addReferenceSkill(0, this.skill, $event.item, 'post');
      } else {
        this.addReferenceSkill(0, this.skill, $event.item, 'pre');
      }
      this.sameSkill = false;
    }
  }

  addReferenceSkill(id, skill1, InputReferenceSkill, classifier) {
    console.log('inside Adding Skills');
    const skill = new ReferenceSkill(id, skill1, InputReferenceSkill, classifier);
    this.referenceSkillName.push(skill);
  }

  submitReferenceSkill() {
    this.referenceSkillName.forEach(element => {
      this.addReferenceSkillForm.controls['id'].patchValue(element.id);
      this.addReferenceSkillForm.controls['skill'].patchValue(this.skill);
      this.allSkills.forEach(element1 => {
        if (element.referenceSkill.name === element1.name) {
          this.addReferenceSkillForm.controls['referenceSkill'].patchValue(element1);
        }
      });
      if (this.type === 1) {
        this.addReferenceSkillForm.controls['classifier'].patchValue('post');
      } else {
        this.addReferenceSkillForm.controls['classifier'].patchValue('pre');
      }
      this.skillService.addReferenceSkill(JSON.stringify(this.addReferenceSkillForm.value)).subscribe();
    });

    this.confirmationDialogService.alert(`Skills Are Added to The Dependancies..`,
      `Skill is Added`)
      .catch(() => console.log('User dismissed the dialog (e.g., by using ESC, clicking the cross icon, or clicking outside the dialog)'));

    delay(3000);
    this.activeModal.dismiss('Cross click');

  }
}
