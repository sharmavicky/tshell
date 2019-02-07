import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScoreAssesmentComponent } from './score-assesment.component';

describe('ScoreAssesmentComponent', () => {
  let component: ScoreAssesmentComponent;
  let fixture: ComponentFixture<ScoreAssesmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScoreAssesmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScoreAssesmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
