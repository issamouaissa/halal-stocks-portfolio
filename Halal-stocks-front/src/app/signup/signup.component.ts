import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  clientname: string = "";
  email: string = "";
  password: string = "";
  isSubmitting: boolean = false;
  errorMessage: string = "";
  successMessage: string = "";

  constructor(private http: HttpClient, private router: Router) {}

  checkAvailability(field: string, value: string) {
    this.http.get(`http://localhost:8085/api/v1/client/check/${field}/${value}`).subscribe(
      (response: any) => {
        if (response.exists) {
          this.errorMessage = `${field.charAt(0).toUpperCase() + field.slice(1)} already exists.`;
        } else {
          this.errorMessage = '';
        }
      },
      (error: any) => {
        console.error(error);
        this.errorMessage = "Error checking availability.";
      }
    );
  }

  save(form: NgForm) {
    if (form.invalid || !this.clientname || !this.email || !this.password) {
      this.errorMessage = "Please fill in all required fields.";
      return;
    }

    this.checkAvailability('email', this.email);
    this.checkAvailability('clientname', this.clientname);

    if (this.errorMessage) return;

    this.isSubmitting = true;
    const bodyData = {
      "clientname": this.clientname,
      "email": this.email,
      "password": this.password
    };

    this.http.post("http://localhost:8085/api/v1/client/save", bodyData, { responseType: 'text' }).subscribe(
      (resultData: any) => {
        console.log(resultData);
        alert("Client Registered Successfully");
        this.router.navigate(['/home']);
      },
      (error: any) => {
        console.error(error);
        alert("Error checking availability: " + error.message);
      }
    );
  }

  googleLogin() {
    window.location.href = 'http://localhost:8085/oauth2/authorization/google';
  }
}
