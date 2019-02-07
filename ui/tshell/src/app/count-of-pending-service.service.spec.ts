import { TestBed } from '@angular/core/testing';

import { CountOfPendingServiceService } from './count-of-pending-service.service';

describe('CountOfPendingServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CountOfPendingServiceService = TestBed.get(CountOfPendingServiceService);
    expect(service).toBeTruthy();
  });
});
