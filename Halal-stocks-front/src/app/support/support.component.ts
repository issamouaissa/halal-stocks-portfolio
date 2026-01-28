import { Component, OnInit } from '@angular/core';
import { ClientService } from '../client.service';
import { Client } from '../client.model';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { SupportService } from '../support.service';

@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.css']
})
export class SupportComponent implements OnInit {
  client: Client | undefined;
  error: string | undefined;
  contact = {
    subject: '',
    body: ''
  };
  selectedFile: File | null = null;
  closeResult = '';
  successMessage: string | undefined;
  errorMessage: string | undefined;

  constructor(
    private clientService: ClientService,
    private supportService: SupportService,
    private route: ActivatedRoute,
    private router: Router,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const clientId = +params['id'];
      if (clientId) {
        this.loadClientData(clientId);
      } else {
        const authenticatedClientId = this.clientService.getAuthenticatedClientId();
        if (authenticatedClientId) {
          this.loadClientData(authenticatedClientId);
        } else {
          this.error = 'Invalid client ID or not authenticated.';
        }
      }
    });
  }

  private loadClientData(id: number) {
    this.clientService.getClientById(id).subscribe(
      (client: Client) => {
        this.client = client;
      },
      error => {
        console.error('Error fetching client data:', error);
        this.error = 'An error occurred while fetching client data.';
      }
    );
  }

  openLogoutModal(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' })
      .result.then(
      (result) => {
        if (result === 'Yes') {
          this.logout();
        }
      },
      (reason) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      }
    );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else {
      return `with: ${reason}`;
    }
  }

  logout() {
    localStorage.removeItem('clientId');
    this.router.navigate(['/home']);
    localStorage.removeItem('watchlistCompanies');
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onSubmit() {
    if (this.client) {
      this.supportService.sendMail(
        'hallalstocks@gmail.com',
        this.contact.subject,
        `From: ${this.client.clientname}\nEmail: ${this.client.email}\n\n${this.contact.body}`
      ).subscribe(
        response => {
          this.successMessage = 'Your message has been sent successfully!';
          this.errorMessage = undefined;  // Clear error message
          // Optionally, clear the form after success
          this.contact.subject = '';
          this.contact.body = '';
        },
        error => {
          this.errorMessage = 'An error occurred while sending your message.';
          this.successMessage = undefined;  // Clear success message
        }
      );
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
