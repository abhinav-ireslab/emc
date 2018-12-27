import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RateExchangerComponent } from './rate-exchanger.component';

describe('RateExchangerComponent', () => {
  let component: RateExchangerComponent;
  let fixture: ComponentFixture<RateExchangerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RateExchangerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RateExchangerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
