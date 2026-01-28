import { Component, OnInit } from '@angular/core';
import { FinancialDataService } from "../financial-data.service";

@Component({
  selector: 'app-stock-screener',
  templateUrl: './stock-screener.component.html',
  styleUrls: ['./stock-screener.component.css']
})
export class StockScreenerComponent implements OnInit {
  companies: any[] = [];
  currentPage = 1;
  pageSize = 30; // Nombre d'entreprises par page

  constructor(private financialDataService: FinancialDataService) { }

  ngOnInit(): void {
    this.loadCompanies();
  }

  loadCompanies(): void {
    this.financialDataService.getAllCompanyData(this.currentPage, this.pageSize).subscribe(data => {
      this.companies = [...this.companies, ...data]; // Add new companies to the existing list
    });
  }

  loadMore(): void {
    this.currentPage++;
    this.loadCompanies();
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
