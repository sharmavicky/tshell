import { TestBed } from '@angular/core/testing';

import { TotalquestionsService } from './totalquestions.service';

describe('TotalquestionsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TotalquestionsService = TestBed.get(TotalquestionsService);
    expect(service).toBeTruthy();
  });
});
