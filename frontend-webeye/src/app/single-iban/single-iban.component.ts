import { Component, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BankInfo } from '../models/bank-info.model';
import { Iban } from "../models/iban.model";
import { IbanService } from "../services/iban.service";

@Component({
  selector: 'app-single-iban',
  templateUrl: './single-iban.component.html',
  styleUrls: ['./single-iban.component.css']
})
export class SingleIbanComponent implements OnInit {

  public isValid: boolean = false;
  public isLoading: boolean = false;
  public bankInfo!: BankInfo;
  public isCorrectIBAN = true;
  private form!: NgForm;

  constructor(private ibanService: IbanService) { }

  ngOnInit(): void {
  }

  onKeyUp(form:NgForm) {
    this.form = form;
  }

  onSubmit(form: NgForm) {
    let name: Iban = form.value;
    this.isLoading = true;
    this.ibanService
      .checkSingleIban(name)
      .subscribe(result => {
        this.isCorrectIBAN = true;
        this.isLoading = false;
        this.onCorrectSubmit(result);
      }, error => {
        this.isLoading = false;
        this.isCorrectIBAN = false;
      })
  }

  private onCorrectSubmit(result: BankInfo) {
    if(result.isValid) {
      this.isValid = true;
      this.isCorrectIBAN = true;
      this.bankInfo = result;
    } else {
      this.isValid = false;
      this.isCorrectIBAN = false;
    }
  }

  clear() {
    this.form.reset();
    this.isValid = false;
    this.isLoading = false;
    this.isCorrectIBAN = true;
  }

}
