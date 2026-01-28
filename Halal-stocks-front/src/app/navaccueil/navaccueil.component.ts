/*
import { Component, TemplateRef, ViewChild, HostListener, ElementRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navaccueil',
  templateUrl: './navaccueil.component.html',
  styleUrls: ['./navaccueil.component.css']
})
export class NavaccueilComponent {
  @ViewChild('logoutModal', { static: false }) logoutModal!: TemplateRef<any>;
  searchTerm: string = ''; // Property to store the search input
  dropdownVisible: boolean = false;

  menuOptions = [
    { label: "Profile Details", icon: "fas fa-user", action: "userprofile" },
    { label: "Requested Stocks", icon: "fas fa-edit", action: "stocks" },
    { label: "Get in touch", icon: "fas fa-envelope", action: "contact" },
    { label: "Log out", icon: "fas fa-sign-out-alt", action: "logout", isLogout: true }
  ];

  constructor(
    public modalService: NgbModal,
    private router: Router,
    private eRef: ElementRef // For detecting clicks outside the dropdown
  ) {}

  toggleDropdown() {
    this.dropdownVisible = !this.dropdownVisible;
  }

  handleAction(action: string) {
    switch (action) {
      case 'userprofile':
        this.router.navigate(['/userprofile']);
        break;
      case 'stocks':
        this.router.navigate(['/requestedstocks']);
        break;
      case 'contact':
        this.router.navigate(['/support']);
        break;
      case 'logout':
        this.openLogoutModal();
        break;
      default:
        console.log("Unknown action");
    }
  }

  openLogoutModal() {
    this.modalService.open(this.logoutModal);
  }

  logout() {
    this.modalService.dismissAll();
    this.router.navigate(['/home']);
    // Add your logout logic here if needed
  }

  onSearch() {
    if (this.searchTerm) {
      // Navigate to the URL with the stock symbol as a part of the path
      this.router.navigate(['/overview', this.searchTerm]);
    }
  }


  // Listener for clicks outside the dropdown
  @HostListener('document:click', ['$event'])
  onClickOutside(event: Event) {
    if (this.dropdownVisible && !this.eRef.nativeElement.contains(event.target)) {
      this.dropdownVisible = false;
    }
  }
}
*/





import { Component, TemplateRef, ViewChild, HostListener, ElementRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-navaccueil',
  templateUrl: './navaccueil.component.html',
  styleUrls: ['./navaccueil.component.css']
})
export class NavaccueilComponent {
  @ViewChild('logoutModal', { static: false }) logoutModal!: TemplateRef<any>;
  searchTerm: string = ''; // Property to store the search input
  dropdownVisible: boolean = false;

  menuOptions = [
    { label: "Profile Details", icon: "fas fa-user", action: "userprofile" },
    { label: "Requested Stocks", icon: "fas fa-edit", action: "stocks" },
    { label: "Get in touch", icon: "fas fa-envelope", action: "contact" },
    { label: "Log out", icon: "fas fa-sign-out-alt", action: "logout", isLogout: true }
  ];

  constructor(
    public modalService: NgbModal,
    private router: Router,
    private eRef: ElementRef, // For detecting clicks outside the dropdown
    private clientService: ClientService // Inject the ClientService
  ) {}

  toggleDropdown() {
    this.dropdownVisible = !this.dropdownVisible;
  }

  handleAction(action: string) {
    switch (action) {
      case 'userprofile':
        this.navigateToUserProfile();
        break;
      case 'stocks':
        this.router.navigate(['/requestedstocks']);
        break;
      case 'contact':
        this.router.navigate(['/support']);
        break;
      case 'logout':
        this.openLogoutModal();
        break;
      default:
        console.log("Unknown action");
    }
  }

  navigateToUserProfile() {
    const clientId = this.clientService.getAuthenticatedClientId();
    if (clientId) {
      this.router.navigate(['/userprofile', clientId]);
    } else {
      console.error('Client ID not found.');
      // Vous pouvez également ajouter une redirection vers une page d'erreur ou de connexion ici
    }
  }



  openLogoutModal() {
    this.modalService.open(this.logoutModal);
  }

  logout() {
    this.modalService.dismissAll();
    this.router.navigate(['/home']);
    localStorage.removeItem('clientId'); // Clear the client ID from local storage
    localStorage.removeItem('watchlistCompanies');
  }

  onSearch() {
    if (this.searchTerm) {
      this.router.navigate(['/overview', this.searchTerm]);
    }
  }

  @HostListener('document:click', ['$event'])
  onClickOutside(event: Event) {
    if (this.dropdownVisible && !this.eRef.nativeElement.contains(event.target)) {
      this.dropdownVisible = false;
    }
  }
}
