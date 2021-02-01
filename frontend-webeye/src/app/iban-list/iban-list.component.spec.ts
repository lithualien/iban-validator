import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IbanListComponent } from './iban-list.component';

describe('IbanListComponent', () => {
  let component: IbanListComponent;
  let fixture: ComponentFixture<IbanListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IbanListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IbanListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
