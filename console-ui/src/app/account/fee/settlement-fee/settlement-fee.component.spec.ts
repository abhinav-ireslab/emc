import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SettlementFeeComponent } from './settlement-fee.component';

describe('SettlementFeeComponent', () => {
  let component: SettlementFeeComponent;
  let fixture: ComponentFixture<SettlementFeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SettlementFeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SettlementFeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
