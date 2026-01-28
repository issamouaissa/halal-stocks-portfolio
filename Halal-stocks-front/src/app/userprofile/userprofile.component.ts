import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../client.service';
import { PasswordresetService } from '../passwordreset.service';
import { Client } from '../client.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-userprofile',
  templateUrl: './userprofile.component.html',
  styleUrls: ['./userprofile.component.css']
})
export class UserprofileComponent implements OnInit {
  client: Client | undefined;
  error: string | undefined;
  isEditing: boolean = false;
  resetForm: FormGroup;
  closeResult = '';
  successMessage: string | undefined;
  errorMessage: string | undefined;


  constructor(
    private clientService: ClientService,
    private passwordResetService: PasswordresetService,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private router: Router,
    private modalService: NgbModal
  ) {
    // Initialize the password reset form with confirmation validation
    this.resetForm = this.fb.group({
      currentPassword: ['', Validators.required],
      newPassword: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]
    }, { validator: this.passwordMatchValidator });
  }

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

  toggleEditMode() {
    this.isEditing = !this.isEditing;
  }

  updateClient() {
    if (this.client) {
      this.clientService.updateClient(this.client).subscribe(
        () => {
          this.isEditing = false;
          this.successMessage = 'Client information updated successfully!';
          this.errorMessage = undefined;  // Clear error message
        },
        error => {
          this.errorMessage = 'Error updating client data.';
          this.successMessage = undefined;  // Clear success message
        }
      );
    }
  }

  onSubmit() {
    if (this.resetForm.valid && this.client) {
      const { currentPassword, newPassword } = this.resetForm.value;
      const clientId = this.client.clientid;

      this.passwordResetService.resetPassword(clientId, currentPassword, newPassword)
        .subscribe(
          response => {
            this.successMessage = 'Password reset successfully!';
            this.errorMessage = undefined;  // Clear error message
          },
          error => {
            this.errorMessage = 'Error resetting password.';
            this.successMessage = undefined;  // Clear success message
          }
        );
    }
  }

  private passwordMatchValidator(group: FormGroup): { [key: string]: boolean } | null {
    const newPassword = group.get('newPassword')?.value;
    const confirmPassword = group.get('confirmPassword')?.value;
    return newPassword === confirmPassword ? null : { mismatch: true };
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
    this.modalService.dismissAll(); // Close modals
    localStorage.removeItem('clientId'); // Remove client ID from local storage
    this.router.navigate(['/home']); // Navigate to home page
    localStorage.removeItem('watchlistCompanies');
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
