import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SecurityConfigurationComponent } from './security-configuration.component';

describe('SecurityConfigurationComponent', () => {
  let component: SecurityConfigurationComponent;
  let fixture: ComponentFixture<SecurityConfigurationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SecurityConfigurationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SecurityConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
