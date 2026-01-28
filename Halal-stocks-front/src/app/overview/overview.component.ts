import { Component, OnInit } from '@angular/core';
import { StockService } from '../stock.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css']
})
export class OverviewComponent implements OnInit {
  stockData: any;
  companyLogoUrl: string | null = null;
  companyOutlook: any;
  errorMessage: string | null = null;
  successMessage: string = '';
  symbol: string = '';
  activeTab: string = 'details';

  constructor(
    private stockService: StockService,
    private sanitizer: DomSanitizer,
    private route: ActivatedRoute
  ) { }

  setActiveTab(tab: string) {
    this.activeTab = tab; // Met à jour l'onglet actif
  }

  ngOnInit(): void {
    // Get the symbol from the route parameters
    this.route.paramMap.subscribe(params => {
      this.symbol = params.get('symbol') || '';
      // Now, you can fetch stock data based on the symbol
      this.loadStockData(this.symbol);
      this.loadCompanyOutlook(this.symbol);
    });
  }

  private loadStockData(symbol: string): void {
    this.stockService.getData(symbol).subscribe(
      response => {
        if (response && response.length > 0) {
          this.stockData = response[0];
        } else {
          this.errorMessage = 'Data not available for this Stock';
        }
      },
      error => {
        console.error('Error fetching stock data:', error);
        this.errorMessage = 'Data not available for this Stock';
      }
    );
  }

  private loadCompanyOutlook(symbol: string): void {
    this.stockService.getCompanyOutlook(symbol).subscribe(
      response => {
        this.companyOutlook = response;
        this.companyLogoUrl = this.companyOutlook?.profile?.image || null;
      },
      error => {
        console.error('Error fetching company outlook:', error);
        this.errorMessage = 'Data not available for this Stock';
      }
    );
  }




  footerSections = [
    {
      title: 'Company',
      links: [
        { name: 'Community', url: '/community' },
        { name: 'Contact', url: '/contact' },
        { name: 'About', url: '/about' },
        { name: 'Blog', url: '/blog' }
      ]
    },
    {
      title: 'Resources',
      links: [
        { name: 'Zakat Calculator', url: '/zakat-calculator' },
        { name: 'Help Desk', url: '/help-desk' },
        { name: 'Learn', url: '/learn' },
        { name: 'API', url: '/api' }
      ]
    },
    {
      title: 'Legal',
      links: [
        { name: 'Privacy Policy', url: '/privacy-policy' },
        { name: 'Terms of Use', url: '/terms-of-use' }
      ]
    }
  ];
}
