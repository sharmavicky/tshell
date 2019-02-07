import { TestBed } from '@angular/core/testing';

import { AssessmenthistoryService } from './assessmenthistory.service';

describe('AssessmenthistoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AssessmenthistoryService = TestBed.get(AssessmenthistoryService);
    expect(service).toBeTruthy();
  });
});
