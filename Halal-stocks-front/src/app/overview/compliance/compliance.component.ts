import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-compliance',
  templateUrl: './compliance.component.html',
  styleUrls: ['./compliance.component.scss']
})
export class ComplianceComponent implements OnInit {

  complianceStatus: any;

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  ngOnInit(): void {
    // Abonnement pour récupérer le symbole de la route
    this.route.parent?.paramMap.subscribe(params => {
      const symbol = params.get('symbol');  // Récupérer le symbole de la route
      if (symbol) {
        this.getComplianceStatus(symbol);  // Charger le statut de conformité en fonction du symbole
      }
    });
  }

  getComplianceStatus(symbol: string) {
    this.http.get(`http://localhost:8085/shariah-compliance?symbol=${symbol}`)
      .pipe(
        catchError(error => {
          console.error('Erreur lors de la récupération des données', error);
          return of(null);  // Retourne une valeur par défaut en cas d'erreur
        })
      )
      .subscribe(data => {
        this.complianceStatus = data;
      });
  }
}
