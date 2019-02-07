import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchExistingQuestionsComponent } from './search-existing-questions.component';

describe('SearchExistingQuestionsComponent', () => {
  let component: SearchExistingQuestionsComponent;
  let fixture: ComponentFixture<SearchExistingQuestionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchExistingQuestionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchExistingQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
