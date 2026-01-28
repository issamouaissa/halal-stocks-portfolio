import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  constructor(private router: Router) {}
  logIn(): void {
    this.router.navigate(['/login']);
    // Ajouter une logique de connexion ici si nécessaire
  }

  getStarted(): void {
    this.router.navigate(['/signup']);
    // Ajouter une logique pour démarrer ici si nécessaire
  }

}
