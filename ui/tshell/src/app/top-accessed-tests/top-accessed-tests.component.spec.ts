import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopAccessedTestsComponent } from './top-accessed-tests.component';

describe('TopAccessedTestsComponent', () => {
  let component: TopAccessedTestsComponent;
  let fixture: ComponentFixture<TopAccessedTestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopAccessedTestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopAccessedTestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
