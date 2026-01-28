/*
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  email: string = "";
  password: string = "";
  isSubmitting: boolean = false;
  errorMessage: string = "";

  constructor(private router: Router, private http: HttpClient) {}

  Login() {
    if (!this.email || !this.password) {
      this.errorMessage = "Please fill in all required fields.";
      return;
    }

    this.isSubmitting = true;
    const bodyData = {
      email: this.email,
      password: this.password,
    };

    this.http.post("http://localhost:8085/api/v1/client/login", bodyData).subscribe(
      (resultData: any) => {
        if (resultData.message === "Email not exits") {
          this.errorMessage = "Email does not exist.";
        } else if (resultData.message === "Login Success") {
          this.router.navigateByUrl('/accueil');
        } else {
          this.errorMessage = "Incorrect Email or Password.";
        }
        this.isSubmitting = false;
      },
      (error: any) => {
        console.error("Login Error: ", error);
        this.errorMessage = "An unexpected error occurred during login.";
        this.isSubmitting = false;
      }
    );
  }
  googleLogin() {
    window.location.href = 'http://localhost:8085/oauth2/authorization/google';
  }
}

*/





import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  email: string = "";
  password: string = "";
  isSubmitting: boolean = false;
  errorMessage: string = "";

  constructor(private router: Router, private http: HttpClient) {}

  Login() {
    if (!this.email || !this.password) {
      this.errorMessage = "Please fill in all required fields.";
      return;
    }

    this.isSubmitting = true;
    const bodyData = {
      email: this.email,
      password: this.password,
    };

    this.http.post("http://localhost:8085/api/v1/client/login", bodyData).subscribe(
      (resultData: any) => {
        if (resultData.message === "Email not exists") {
          this.errorMessage = "Email does not exist.";
        } else if (resultData.message === "Login Success") {
          const clientId = resultData.clientId; // Correctly extract clientId from resultData
          localStorage.setItem('clientId', clientId.toString());
          console.log('Stored clientId:', localStorage.getItem('clientId'));
          this.router.navigateByUrl('/accueil');
        } else {
          this.errorMessage = "Incorrect email or password.";
        }
        this.isSubmitting = false;
      },
      (error: any) => {
        console.error("Login Error: ", error);
        this.errorMessage = "An unexpected error occurred during login.";
        this.isSubmitting = false;
      }
    );

  }
  googleLogin() {
    window.location.href = 'http://localhost:8085/oauth2/authorization/google';
  }
}

