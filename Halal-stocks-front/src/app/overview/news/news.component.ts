import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StockService} from "../../stock.service";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {
  CompanyNews: any[] = [];

  constructor(
    private stockService: StockService,
    private route: ActivatedRoute  // Injection de ActivatedRoute
  ) { }

  ngOnInit(): void {
    // Abonnement pour récupérer le symbole de la route
    this.route.parent?.paramMap.subscribe(params => {
      const symbol = params.get('symbol');  // Récupérer le symbole de la route
      if (symbol) {
        this.fetchCompanyNews(symbol);  // Charger les actualités en fonction du symbole
      }
    });
  }

  fetchCompanyNews(symbol: string): void {
    const page = 0;
    const from = '2024-01-01';
    const to = '2024-12-01';

    this.stockService.getCompanyNews(symbol, page, from, to)
      .subscribe(
        data => {
          this.CompanyNews = data;
          console.log('Company News:', this.CompanyNews);
        },
        error => {
          console.error('Error fetching news', error);
        }
      );
  }
}
