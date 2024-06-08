import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { CurrencyListComponent } from './currency-list/currency-list.component';
import { ChartComponent } from './chart/chart.component';

export const routes: Routes = [
	{path: '', redirectTo: 'currencies', pathMatch: 'full'},
	{path: 'currencies', component: CurrencyListComponent},
	{path: 'currency/chart/:code', component: ChartComponent}
	];

