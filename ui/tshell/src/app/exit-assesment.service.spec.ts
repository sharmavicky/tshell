import { TestBed } from '@angular/core/testing';

import { ExitAssesmentService } from './exit-assesment.service';

describe('ExitAssesmentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ExitAssesmentService = TestBed.get(ExitAssesmentService);
    expect(service).toBeTruthy();
  });
});
