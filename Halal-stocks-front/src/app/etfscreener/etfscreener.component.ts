import {Component, OnInit} from '@angular/core';
import {FinancialDataService} from "../financial-data.service";

@Component({
  selector: 'app-etfscreener',
  templateUrl: './etfscreener.component.html',
  styleUrl: './etfscreener.component.css'
})
export class EtfscreenerComponent implements OnInit {
  companies: any[] = [];
  currentPage = 1;
  pageSize = 30; // Number of companies per page
  etfs: any[] = [];
  constructor(private financialDataService: FinancialDataService) { }

  ngOnInit(): void {
    this.loadEtfs();
  }

  loadEtfs(): void {
    this.financialDataService.getAllEftData(this.currentPage, this.pageSize).subscribe(data => {
      this.etfs = [...this.etfs, ...data]; // Ajouter les nouveaux ETFs à la liste existante
    });
  }

  loadMore(): void {
    this.currentPage++;
    this.loadEtfs();
  }



  footerSections = [
    {
      title: 'Company',
      links: [
        {name: 'Community', url: '/community'},
        {name: 'Contact', url: '/contact'},
        {name: 'About', url: '/about'},
        {name: 'Blog', url: '/blog'}
      ]
    },
    {
      title: 'Resources',
      links: [
        {name: 'Zakat Calculator', url: '/zakat-calculator'},
        {name: 'Help Desk', url: '/help-desk'},
        {name: 'Learn', url: '/learn'},
        {name: 'API', url: '/api'}
      ]
    },
    {
      title: 'Legal',
      links: [
        {name: 'Privacy Policy', url: '/privacy-policy'},
        {name: 'Terms of Use', url: '/terms-of-use'}
      ]
    }
  ];

}
