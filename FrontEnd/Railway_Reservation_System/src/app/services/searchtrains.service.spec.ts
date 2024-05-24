import { TestBed } from '@angular/core/testing';

import { SearchtrainsService } from './searchtrains.service';

describe('SearchtrainsService', () => {
  let service: SearchtrainsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchtrainsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
