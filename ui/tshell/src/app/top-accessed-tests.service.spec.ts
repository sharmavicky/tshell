import { TestBed } from '@angular/core/testing';

import { TopAccessedTestsService } from './top-accessed-tests.service';

describe('TopAccessedTestsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TopAccessedTestsService = TestBed.get(TopAccessedTestsService);
    expect(service).toBeTruthy();
  });
});
