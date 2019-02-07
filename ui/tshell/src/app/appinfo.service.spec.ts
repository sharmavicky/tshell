import { TestBed } from '@angular/core/testing';

import { AppinfoService } from './appinfo.service';

describe('AppinfoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AppinfoService = TestBed.get(AppinfoService);
    expect(service).toBeTruthy();
  });
});
