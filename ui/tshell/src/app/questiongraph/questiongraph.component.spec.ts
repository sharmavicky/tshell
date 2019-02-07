import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestiongraphComponent } from './questiongraph.component';

describe('QuestiongraphComponent', () => {
  let component: QuestiongraphComponent;
  let fixture: ComponentFixture<QuestiongraphComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuestiongraphComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestiongraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
