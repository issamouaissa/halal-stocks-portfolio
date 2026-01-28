import { environment } from "./environment";
import { HttpClient } from "@angular/common/http";
import { Observable, throwError } from "rxjs";
import { Client } from "./client.model";
import { catchError } from "rxjs/operators";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiUrl = `${environment.apiUrl}/api/v1/client`;

  constructor(private http: HttpClient) { }

  getClientById(id: number): Observable<Client> {
    return this.http.get<Client>(`${this.apiUrl}/find/${id}`).pipe(
      catchError((error: any) => {
        console.error('Error fetching client data:', error);
        return throwError(() => new Error('Failed to fetch client data.'));
      })
    );
  }

  updateClient(client: Client): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/update/${client.clientid}`, client).pipe(
      catchError((error: any) => {
        console.error('Error updating client data:', error);
        return throwError(() => new Error('Failed to update client data.'));
      })
    );
  }


  getAuthenticatedClientId(): number | null {
    const clientId = localStorage.getItem('clientId');
    console.log('Retrieved clientId:', clientId); // Vérification ici
    return clientId ? +clientId : null;
  }
}
