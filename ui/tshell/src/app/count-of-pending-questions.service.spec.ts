import { TestBed } from '@angular/core/testing';

import { CountOfPendingQuestionsService } from './count-of-pending-questions.service';

describe('CountOfPendingQuestionsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CountOfPendingQuestionsService = TestBed.get(CountOfPendingQuestionsService);
    expect(service).toBeTruthy();
  });
});
