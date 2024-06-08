import { Component, OnInit} from '@angular/core';
import { Currency } from '../model/currency';
import { CurrencyListService } from '../service/currency-list.service'
import { formatDate } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-currency-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './currency-list.component.html',
  styleUrl: './currency-list.component.css'
})
export class CurrencyListComponent implements OnInit {
  currDate = formatDate(new Date(), 'yyyy-MM-dd', 'en');
  currencies!: Currency[];

  constructor(private currencyService: CurrencyListService,
    private router: Router) {}

  loadCurrency() {
    this.currencyService.findCurrency(this.currDate).subscribe(data => {
      this.currencies = data;
    });
  }

  onChart(code: string) {
    this.router.navigate(['currency/chart', code]);
  }

  ngOnInit(): void {
    this.loadCurrency();
  }
}
