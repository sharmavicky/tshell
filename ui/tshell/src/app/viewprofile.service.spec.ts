import { TestBed } from '@angular/core/testing';

import { ViewprofileService } from './viewprofile.service';

describe('ViewprofileService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ViewprofileService = TestBed.get(ViewprofileService);
    expect(service).toBeTruthy();
  });
});
