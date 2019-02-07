import { TestBed } from '@angular/core/testing';

import { SearchTop5SkillsService } from './search-top5-skills.service';

describe('SearchTop5SkillsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SearchTop5SkillsService = TestBed.get(SearchTop5SkillsService);
    expect(service).toBeTruthy();
  });
});
