import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../company.service';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit {
  companies: any[] = [];
  news: any[] = [];
  errorMessage: string = '';
  currentPage: number = 1;
  pageSize: number = 30;
  selectedFilter: string = 'mostActive'; // Default filter

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    this.loadCompanies();
    this.loadNews();
  }

  loadCompanies(filterType?: string): void {
    this.companyService.getAllCompanies(this.currentPage, this.pageSize).subscribe({
      next: (data: any[]) => {
        const symbols = data.map(company => company.symbol);
        this.companyService.getCompaniesDetails(symbols).subscribe(details => {
          this.companies = [...this.companies, ...details];
          if (filterType) {
            this.applyFilter(filterType);
          }
        });
      },
      error: (error) => {
        this.errorMessage = 'Failed to load companies';
      }
    });
  }

  loadNews(): void {
    this.companyService.getLatestNews().subscribe({
      next: (news: any[]) => {
        this.news = news;
      },
      error: (error) => {
        this.errorMessage = 'Failed to load news';
      }
    });
  }

  loadMore(): void {
    this.currentPage++;
    this.loadCompanies(this.selectedFilter);
  }

  filter(type: string): void {
    this.selectedFilter = type;
    this.companies = []; // Clear the current companies
    this.currentPage = 1; // Reset page number to 1
    this.loadCompanies(type);
  }

  private applyFilter(type: string): void {
    // Implement filter logic based on the type
    console.log(`Applying filter: ${type}`);
    // Example of applying filter based on type
    if (type === 'mostActive') {
      this.companies.sort((a, b) => b.volume - a.volume); // Example sorting
    } else if (type === 'mostPopular') {
      this.companies.sort((a, b) => b.marketCap - a.marketCap); // Example sorting
    } else if (type === 'topGainers') {
      this.companies.sort((a, b) => b.change - a.change); // Example sorting
    } else if (type === 'topLosers') {
      this.companies.sort((a, b) => a.change - b.change); // Example sorting
    }
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
