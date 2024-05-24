import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableTrainComponent } from './available-train.component';

describe('AvailableTrainComponent', () => {
  let component: AvailableTrainComponent;
  let fixture: ComponentFixture<AvailableTrainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AvailableTrainComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AvailableTrainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
