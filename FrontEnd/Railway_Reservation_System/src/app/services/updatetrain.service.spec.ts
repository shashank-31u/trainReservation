import { TestBed } from '@angular/core/testing';

import { UpdatetrainService } from './updatetrain.service';

describe('UpdatetrainService', () => {
  let service: UpdatetrainService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UpdatetrainService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
