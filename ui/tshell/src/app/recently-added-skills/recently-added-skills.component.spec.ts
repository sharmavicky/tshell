import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecentlyAddedSkillsComponent } from './recently-added-skills.component';

describe('RecentlyAddedSkillsComponent', () => {
  let component: RecentlyAddedSkillsComponent;
  let fixture: ComponentFixture<RecentlyAddedSkillsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecentlyAddedSkillsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecentlyAddedSkillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
