import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LearnerHomepageComponent } from './learner-homepage.component';

describe('LearnerHomepageComponent', () => {
  let component: LearnerHomepageComponent;
  let fixture: ComponentFixture<LearnerHomepageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LearnerHomepageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LearnerHomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
