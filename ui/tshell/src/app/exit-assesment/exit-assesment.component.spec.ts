import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExitAssesmentComponent } from './exit-assesment.component';

describe('ExitAssesmentComponent', () => {
  let component: ExitAssesmentComponent;
  let fixture: ComponentFixture<ExitAssesmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExitAssesmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExitAssesmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
