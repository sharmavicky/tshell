import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GraphmodalComponent } from './graphmodal.component';

describe('GraphmodalComponent', () => {
  let component: GraphmodalComponent;
  let fixture: ComponentFixture<GraphmodalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GraphmodalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GraphmodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
