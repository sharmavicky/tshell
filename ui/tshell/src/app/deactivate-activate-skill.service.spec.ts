import { TestBed } from '@angular/core/testing';

import { DeactivateActivateSkillService } from './deactivate-activate-skill.service';

describe('DeactivateActivateSkillService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DeactivateActivateSkillService = TestBed.get(DeactivateActivateSkillService);
    expect(service).toBeTruthy();
  });
});
