import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Currency } from '../model/currency';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CurrencyListService {

  private currencyUrl: string;

  constructor(private http: HttpClient) {
    this.currencyUrl = 'http://localhost:8081/currency-rate/api/currencies';
  }

  public findCurrency(date: string): Observable<Currency[]> {
    const url = this.currencyUrl + "/" + date;
    return this.http.get<Currency[]>(url);
  }

  public findCurrencyByCode(code: string): Observable<Currency[]> {
    const url = this.currencyUrl + "/currency/" + code;
    return this.http.get<Currency[]>(url);
  }
}
