import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SupportService {

  private baseUrl = 'http://localhost:8085/mail/send';

  constructor(private http: HttpClient) { }

  sendMail(mail: string, subject: string, message: string): Observable<any> {
    const body = {
      subject: subject,
      message: message
    };
    return this.http.post(`${this.baseUrl}/${mail}`, body, { responseType: 'text' });
  }
}
