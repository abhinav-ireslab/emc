import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WithrawalFeeComponent } from './withrawal-fee.component';

describe('WithrawalFeeComponent', () => {
  let component: WithrawalFeeComponent;
  let fixture: ComponentFixture<WithrawalFeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WithrawalFeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WithrawalFeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
