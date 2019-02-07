import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CountOfQuestionsToReviewComponent } from './count-of-questions-to-review.component';

describe('CountOfQuestionsToReviewComponent', () => {
  let component: CountOfQuestionsToReviewComponent;
  let fixture: ComponentFixture<CountOfQuestionsToReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CountOfQuestionsToReviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CountOfQuestionsToReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
