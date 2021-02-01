import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BankInfo } from '../models/bank-info.model';
import { Iban } from '../models/iban.model';


@Injectable({ providedIn: 'root' })
export class IbanService {

    private url = "http://totaurestapi-env.eba-dfyvcstc.eu-central-1.elasticbeanstalk.com/api/iban/validate";

    constructor(private http: HttpClient) {}

    checkSingleIban(iban: Iban): Observable<BankInfo> {
        return this.http
            .post<BankInfo>(this.url, iban);
    }

    checkArrayOfIban(ibans: Array<string>) {
        return this.http
            .post<Array<BankInfo>>(this.url + "/list", ibans);
    }

}