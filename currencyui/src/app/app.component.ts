import { Component } from '@angular/core';
import { formatDate } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { CurrencyListComponent } from './currency-list/currency-list.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CurrencyListComponent, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  currDate = formatDate(new Date(), 'yyyy-MM-dd', 'en');

}
