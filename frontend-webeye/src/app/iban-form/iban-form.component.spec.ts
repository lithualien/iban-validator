import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IbanFormComponent } from './iban-form.component';

describe('IbanFormComponent', () => {
  let component: IbanFormComponent;
  let fixture: ComponentFixture<IbanFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IbanFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IbanFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
