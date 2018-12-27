import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAccountLedgerComponent } from './user-account-ledger.component';

describe('UserAccountLedgerComponent', () => {
  let component: UserAccountLedgerComponent;
  let fixture: ComponentFixture<UserAccountLedgerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserAccountLedgerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAccountLedgerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
