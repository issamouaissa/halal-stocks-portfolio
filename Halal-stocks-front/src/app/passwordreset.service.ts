import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PasswordresetService {
  private apiUrl = 'http://localhost:8085/api/v1/client/reset-password';

  constructor(private http: HttpClient) { }

  resetPassword(clientId: number, currentPassword: string, newPassword: string): Observable<any> {
    const params = new HttpParams()
      .set('clientId', clientId.toString())
      .set('currentPassword', currentPassword)
      .set('newPassword', newPassword);

    return this.http.post<any>(this.apiUrl, null, { params });
  }

}
