import { Component, Input, OnInit } from '@angular/core';
import { BankInfo } from '../models/bank-info.model';

@Component({
  selector: 'app-iban-form',
  templateUrl: './iban-form.component.html',
  styleUrls: ['./iban-form.component.css']
})
export class IbanFormComponent implements OnInit {

  @Input("bank-info")
  public bankInfo!: BankInfo;

  constructor() {}

  ngOnInit(): void {
  }

  isSeb(): string {
    if(this.bankInfo.isSeb) {
      return "Taip";
    } else {
      return "Ne";
    }
  }

  isBranchNameNameEmpty(): string {
    if(this.bankInfo.branchName.length > 0) {
      return this.bankInfo.branchName;
    } else {
      return "-";
    }
  }

}
