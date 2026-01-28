import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StockService} from "../../stock.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  companyProfile: any;

  constructor(
    private stockService: StockService,
    private route: ActivatedRoute  // Injection de ActivatedRoute
  ) { }

  ngOnInit(): void {
    // Abonnement pour récupérer le symbole de la route
    this.route.parent?.paramMap.subscribe(params => {
      const symbol = params.get('symbol');
      if (symbol) {
        this.loadCompanyProfile(symbol);  // Charger les données du profil
      }
    });
  }

  // Méthode pour charger les données du profil de l'entreprise
  loadCompanyProfile(symbol: string): void {
    this.stockService.getCompanyOutlook(symbol).subscribe(
      data => {
        this.companyProfile = data.profile;  // Accéder à la propriété "profile" dans la réponse
        console.log('Company Profile:', this.companyProfile);
      },
      error => {
        console.error('Error fetching company profile:', error);
      }
    );
  }
}
