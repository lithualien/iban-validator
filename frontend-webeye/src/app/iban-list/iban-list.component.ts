import { ViewportScroller } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BankInfo } from '../models/bank-info.model';
import { IbanService } from "../services/iban.service";

@Component({
  selector: 'app-iban-list',
  templateUrl: './iban-list.component.html',
  styleUrls: ['./iban-list.component.css']
})
export class IbanListComponent implements OnInit {

  public numbers:Array<number> = [];
  public ibanNumbers: Array<string> = [];
  public amount: number = 2;
  private form!: NgForm;
  public bankInfoArray!: Array<BankInfo>;
  public isArrayReceived: boolean = false;
  public isLoading: boolean = false;
  public showForm = false;
  public bankInfo!: BankInfo;

  constructor(private ibanService: IbanService, private viewportScroller: ViewportScroller) { }

  
  ngOnInit(): void {
    this.setNumbersArray();
  }

  onSubmit() {
    this.isLoading = true;
    this.showForm = false;
    this.ibanService.checkArrayOfIban(this.ibanNumbers)
      .subscribe(result => {
        this.isLoading = false;
        this.bankInfoArray = result;
        this.isArrayReceived = true;
      }, error => {
        this.isLoading = false;
        this.isArrayReceived = false;
      })
  }

  getStatus(isValid: boolean) {
    if(isValid) {
      return "Galiojantis";
    } else {
      return "Negaliojantis";
    }
  }

  clear() {
    if(this.form !== undefined) {
      this.form.reset();
    }
    
    this.ibanNumbers = [];
    this.isLoading = false;
    this.isArrayReceived = false;
    this.showForm = false;
  }

  onKeyUp(form: NgForm) {
    this.form = form;
  }

  onKey(event: any) {

    if(event.target.value < this.amount) {
      this.ibanNumbers.splice(event.target.value, 1);
    }
    if(event.target.value > 10) {
      this.amount = 10;
    }
    else if(event.target.value < 1 && Object.keys(event.target.value).length != 0) {
      this.amount = 1
    } else {
      this.amount = event.target.value;
    }
    this.ibanNumbers.splice(event.target.value, 1);
    this.setNumbersArray();
  }

  private setNumbersArray() {
    this.numbers = Array(this.amount);
    for(let i = 0; i < this.amount; i++) {
      this.numbers[i] = i;
    }
  }

  public onClick(bankInfo: BankInfo): void { 
    this.bankInfo = bankInfo;
    this.showForm = true;
}

}
  