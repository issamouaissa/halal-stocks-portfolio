import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../company.service';

@Component({
  selector: 'app-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {
  watchlists: string[] = ['My Watchlist'];
  selectedWatchlist: string | undefined;
  enteredSymbol: string = '';
  companyData: any[] = [];
  successMessage: string | null = null;
  errorMessage: string | null = null;
  showConfirmationDialog: boolean = false;
  loading: boolean = false; // Indicateur de chargement

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    const clientId = localStorage.getItem('clientId'); // Récupérer l'ID du client
    if (clientId) {
      const storedCompanies = localStorage.getItem(`watchlistCompanies_${clientId}`);
      if (storedCompanies) {
        this.companyData = JSON.parse(storedCompanies);
      }
    }
  }

  createWatchlist(): void {
    if (this.enteredSymbol) {
      const existingCompany = this.companyData.find(company => company.symbol === this.enteredSymbol);
      if (existingCompany) {
        this.errorMessage = 'This stock already exists in the watchlist.';
        this.successMessage = null;
        return;
      }

      this.loading = true; // Démarrer le chargement
      this.companyService.getCompaniesDetails([this.enteredSymbol]).subscribe({
        next: (data: any[]) => {
          this.loading = false; // Arrêter le chargement
          if (data.length > 0) {
            this.companyData.push(data[0]);
            this.saveToLocalStorage();
            this.successMessage = 'Company added successfully!';
            this.errorMessage = null;
            this.resetMessages();
          } else {
            this.errorMessage = 'No company found for this symbol.';
          }
        },
        error: (error) => {
          this.loading = false; // Arrêter le chargement
          this.errorMessage = 'Failed to load company data. Please try again later.';
          console.error('Failed to load company data', error);
        }
      });
    }
  }

  saveToLocalStorage(): void {
    const clientId = localStorage.getItem('clientId');
    if (clientId) {
      localStorage.setItem(`watchlistCompanies_${clientId}`, JSON.stringify(this.companyData)); // Utiliser un ID unique
    }
  }

  deleteCompany(symbol: string): void {
    this.companyData = this.companyData.filter(company => company.symbol !== symbol);
    this.saveToLocalStorage();
  }

  confirmDeleteWatchlist(): void {
    this.showConfirmationDialog = true;
  }

  confirmDelete(): void {
    this.companyData = [];
    this.saveToLocalStorage();
    this.successMessage = 'All companies have been deleted from the watchlist.';
    this.errorMessage = null;
    this.showConfirmationDialog = false;
  }

  cancelDelete(): void {
    this.showConfirmationDialog = false;
  }

  private resetMessages(): void {
    setTimeout(() => {
      this.successMessage = null;
      this.errorMessage = null;
    }, 3000);
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
