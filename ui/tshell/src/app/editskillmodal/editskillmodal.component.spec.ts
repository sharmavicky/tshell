import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditskillmodalComponent } from './editskillmodal.component';

describe('EditskillmodalComponent', () => {
  let component: EditskillmodalComponent;
  let fixture: ComponentFixture<EditskillmodalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditskillmodalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditskillmodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
