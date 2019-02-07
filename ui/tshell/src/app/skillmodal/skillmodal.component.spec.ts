import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillmodalComponent } from './skillmodal.component';

describe('SkillmodalComponent', () => {
  let component: SkillmodalComponent;
  let fixture: ComponentFixture<SkillmodalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SkillmodalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SkillmodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
