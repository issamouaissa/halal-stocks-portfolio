import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class StockService {
  private readonly apiKey = 'Jo81hUb49jsgogfc7uzIlYGKlKmxINWD';  // Centralized API key
  private baseUrl = 'https://financialmodelingprep.com/api/v3/quote-order';
  private outlookUrl = 'https://financialmodelingprep.com/api/v4/company-outlook';
  private newsUrl = 'https://financialmodelingprep.com/api/v3/stock_news';
  private apiUrl = 'http://localhost:8085/api/watchlist';

  constructor(private http: HttpClient) { }

  getData(symbol: string): Observable<any> {
    const url = `${this.baseUrl}/${symbol}?apikey=${this.apiKey}`;
    return this.http.get<any>(url).pipe(catchError(this.handleError));
  }

  getCompanyOutlook(symbol: string): Observable<any> {
    const url = `${this.outlookUrl}?symbol=${symbol}&apikey=${this.apiKey}`;
    return this.http.get<any>(url).pipe(catchError(this.handleError));
  }

  getCompanyNews(tickers: string, page: number, from: string, to: string): Observable<any> {
    const url = `${this.newsUrl}?tickers=${tickers}&page=${page}&from=${from}&to=${to}&apikey=${this.apiKey}`;
    return this.http.get<any>(url).pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Client-side error: ${error.error.message}`;
    } else {
      // Server-side error
      errorMessage = `Server-side error: ${error.status} - ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(errorMessage);
  }
}
