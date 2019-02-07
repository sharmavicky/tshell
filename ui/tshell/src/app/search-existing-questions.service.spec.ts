import { TestBed } from '@angular/core/testing';

import { SearchExistingQuestionsService } from './search-existing-questions.service';

describe('SearchExistingQuestionsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchExistingQuestionsService = TestBed.get(SearchExistingQuestionsService);
    expect(service).toBeTruthy();
  });
});
