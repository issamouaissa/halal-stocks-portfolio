import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private stockListUrl = 'https://financialmodelingprep.com/api/v3/stock/list?apikey=Jo81hUb49jsgogfc7uzIlYGKlKmxINWD';
  private etfListUrl = 'https://financialmodelingprep.com/api/v3/etf/list?apikey=Jo81hUb49jsgogfc7uzIlYGKlKmxINWD';
  private quoteApiUrl = 'https://financialmodelingprep.com/api/v3/quote/';
  private profileApiUrl = 'https://financialmodelingprep.com/api/v3/profile/';
  private apiKey = 'Jo81hUb49jsgogfc7uzIlYGKlKmxINWD';
  private newsApiUrl = 'https://financialmodelingprep.com/api/v3/stock_news?apikey=Jo81hUb49jsgogfc7uzIlYGKlKmxINWD';
  private complianceUrl = 'http://localhost:8085/shariah-compliance';

  constructor(private http: HttpClient) {}

  // Fetch stock and ETF companies combined with pagination
  getAllCompanies(page: number, pageSize: number): Observable<any[]> {
    return forkJoin([
      this.getCompaniesByPage(this.stockListUrl, page, pageSize),
      this.getCompaniesByPage(this.etfListUrl, page, pageSize)
    ]).pipe(
      map(([stocks, etfs]) => [...stocks, ...etfs]) // Combine both lists
    );
  }

  // Fetch companies by page for a given API URL
  // Fetch companies by page for a given API URL
  private getCompaniesByPage(apiUrl: string, page: number, pageSize: number): Observable<any[]> {
    return this.http.get<any[]>(apiUrl).pipe(
      map(companies => companies.slice((page - 1) * pageSize, page * pageSize)) // Pagination
    );
  }

  // Fetch company details (name, price, change, marketCap, compliance status)
  getCompaniesDetails(symbols: string[]): Observable<any[]> {
    const detailsRequests = symbols.map(symbol =>
      this.http.get<any>(`${this.quoteApiUrl}${symbol}?apikey=${this.apiKey}`).pipe(
        map((details: any) => ({
          symbol,
          name: details[0]?.name || symbol,
          price: details[0]?.price || 'N/A',
          change: details[0]?.change || 'N/A',
          marketCap: details[0]?.marketCap || 'N/A'
        }))
      )
    );

    const profileRequests = symbols.map(symbol =>
      this.http.get<any[]>(`${this.profileApiUrl}${symbol}?apikey=${this.apiKey}`).pipe(
        map((profiles: any[]) => ({
          symbol,
          image: profiles[0]?.image || 'default-image-url'
        }))
      )
    );

    // Fetch compliance status
    const complianceRequests = symbols.map(symbol =>
      this.http.get<any>(`${this.complianceUrl}?symbol=${symbol}`).pipe(
        map((compliance: any) => ({
          symbol,
          complianceStatus: compliance?.status || 'N/A'
        }))
      )
    );

    return forkJoin([forkJoin(detailsRequests), forkJoin(profileRequests), forkJoin(complianceRequests)]).pipe(
      map(([details, profiles, compliance]) => details.map(detail => ({
        ...detail,
        image: profiles.find(p => p.symbol === detail.symbol)?.image || 'default-image-url',
        complianceStatus: compliance.find(c => c.symbol === detail.symbol)?.complianceStatus || 'N/A'
      })))
    );
  }

  // Fetch the latest news
  getLatestNews(): Observable<any[]> {
    return this.http.get<any[]>(this.newsApiUrl).pipe(
      map((news: any[]) => news.map(article => ({
        title: article.title,
        description: article.description,
        url: article.url,
        publishedDate: article.publishedDate,
        image: article.image || 'default-news-image-url'
      })))
    );
  }
}
