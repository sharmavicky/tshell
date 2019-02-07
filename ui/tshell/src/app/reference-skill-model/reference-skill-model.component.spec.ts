import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReferenceSkillModelComponent } from './reference-skill-model.component';

describe('ReferenceSkillModelComponent', () => {
  let component: ReferenceSkillModelComponent;
  let fixture: ComponentFixture<ReferenceSkillModelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReferenceSkillModelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReferenceSkillModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
