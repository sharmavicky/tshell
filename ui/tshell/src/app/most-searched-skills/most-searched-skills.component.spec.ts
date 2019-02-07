import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MostSearchedSkillsComponent } from './most-searched-skills.component';

describe('MostSearchedSkillsComponent', () => {
  let component: MostSearchedSkillsComponent;
  let fixture: ComponentFixture<MostSearchedSkillsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MostSearchedSkillsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MostSearchedSkillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
