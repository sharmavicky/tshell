import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssessmenthistoryComponent } from './assessmenthistory.component';

describe('AssessmenthistoryComponent', () => {
  let component: AssessmenthistoryComponent;
  let fixture: ComponentFixture<AssessmenthistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssessmenthistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssessmenthistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
