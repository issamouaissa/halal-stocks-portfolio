import { Component } from '@angular/core';
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";
import {StockService} from "../../stock.service";
import {ActivatedRoute} from "@angular/router";
import * as https from "https";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent {
  tradingViewUrl: SafeResourceUrl = '';
  stockData: any;

  constructor(
    private stockService: StockService,
    private sanitizer: DomSanitizer,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.parent?.paramMap.subscribe(params => {
      const symbol = params.get('symbol');
      if (symbol) {
        this.setTradingViewUrl(symbol);
        this.loadStockData(symbol);
      }
    });
  }

  private loadStockData(symbol: string): void {
    this.stockService.getData(symbol).subscribe(
      response => {
        this.stockData = response[0];
        console.log(this.stockData);
      },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  private setTradingViewUrl(symbol: string): void {
    const url = `https://s.tradingview.com/embed-widget/symbol-overview/?locale=en#%7B%22symbols%22%3A%5B%5B%22${symbol}%22%5D%5D%2C%22width%22%3A%22100%25%22%2C%22height%22%3A%22300px%22%2C%22colorTheme%22%3A%22light%22%2C%22scalePosition%22%3A%22right%22%2C%22scaleMode%22%3A%22Normal%22%2C%22chartType%22%3A%22line%22%2C%22color%22%3A%22%231BAD4E%22%2C%22fontFamily%22%3A%22-apple-system%2C%20BlinkMacSystemFont%2C%20Trebuchet%20MS%2C%20Roboto%2C%20Ubuntu%2C%20sans-serif%22%2C%22fontSize%22%3A%2210%22%2C%22valuesTracking%22%3A%221%22%2C%22changeMode%22%3A%22price-and-percent%22%2C%22chartOnly%22%3A%22%22%7D`;
    this.tradingViewUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
}

