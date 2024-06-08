import { CommonModule } from '@angular/common';
import { Component, OnInit, Input } from '@angular/core';
import { CurrencyListService } from '../service/currency-list.service'
import { Currency } from '../model/currency';
import { Router, ActivatedRoute} from '@angular/router';
import { CanvasJSAngularChartsModule } from '@canvasjs/angular-charts';
import { RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-chart',
  standalone: true,
  imports: [CommonModule, RouterOutlet, CanvasJSAngularChartsModule],
  templateUrl: './chart.component.html',
  styleUrl: './chart.component.css'
})
export class ChartComponent implements OnInit {
  currCode = '';
  currencies!: Currency[];
  dps: { x: Date, y: number}[] = [];
  chart: any;
  chartOptions: any;

  constructor(private currencyService: CurrencyListService,
    private router: Router,
    private activRouter: ActivatedRoute) {}

  onClose() {
    this.router.navigate(['currencies']);
  }


  fillChart() {
    this.currencyService.findCurrencyByCode(this.currCode).subscribe(data => {
          this.currencies = data;
          this.dps = this.currencies.map(i => {
            return {x: new Date(i.date), y: i.rate};
          });
          this.chartOptions = {
            exportEnabled: true,
            title: {
              text: 'Currency chart for ' +  data[0].currencyDescription
            },
            data: [{
              type: "line",
              dataPoints: this.dps
            }]
          };
    });
  }

  getChartInstance(chart: object) {
    this.chart = chart;
    this.fillChart();
    this.chart.render();
  }

  ngOnInit() {
    this.currCode = String(this.activRouter.snapshot.paramMap.get('code'));
    this.fillChart();
    console.log(this.currCode);
  }
}
