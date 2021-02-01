import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleIbanComponent } from './single-iban.component';

describe('SingleIbanComponent', () => {
  let component: SingleIbanComponent;
  let fixture: ComponentFixture<SingleIbanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingleIbanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SingleIbanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
