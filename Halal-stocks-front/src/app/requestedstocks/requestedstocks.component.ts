import { Component } from '@angular/core';

@Component({
  selector: 'app-requestedstocks',
  templateUrl: './requestedstocks.component.html',
  styleUrl: './requestedstocks.component.css'
})
export class RequestedstocksComponent {
  title: string = 'My Stock Coverage Requests';
  placeholderText: string = 'Search for stocks';
  emptyMessage: string = 'Looks empty! Once you request coverage for stocks, they\'ll appear here.';
  searchQuery: string = '';

  // Cette méthode sera appelée lors de la recherche
  onSearch() {
    console.log('Search query:', this.searchQuery);
    // Logique supplémentaire pour effectuer la recherche
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
