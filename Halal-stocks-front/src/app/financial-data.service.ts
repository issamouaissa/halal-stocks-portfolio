import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FinancialDataService {
  private listApiUrl = 'https://financialmodelingprep.com/api/v3/stock/list?apikey=Jo81hUb49jsgogfc7uzIlYGKlKmxINWD';
  private quoteApiUrl = 'https://financialmodelingprep.com/api/v3/quote/';
  private profileApiUrl = 'https://financialmodelingprep.com/api/v3/profile/';
  private apiKey = 'Jo81hUb49jsgogfc7uzIlYGKlKmxINWD';
  private etfCompaniesUrl = 'https://financialmodelingprep.com/api/v3/etf/list?apikey=Jo81hUb49jsgogfc7uzIlYGKlKmxINWD';
  private complianceUrl = 'http://localhost:8085/shariah-compliance';

  constructor(private http: HttpClient) { }

  getComplianceStatus(symbols: string[]): Observable<any[]> {
    const complianceRequests = symbols.map(symbol =>
      this.http.get<any>(`http://localhost:8085/shariah-compliance?symbol=${symbol}`).pipe(
        map((compliance: any) => ({
          symbol: symbol,
          status: compliance?.status || 'N/A'
        }))
      )
    );

    return forkJoin(complianceRequests);
  }

  // Fetch companies by page and pageSize
  getCompaniesByPage(page: number, pageSize: number): Observable<any[]> {
    return this.http.get<any[]>(this.listApiUrl).pipe(
      map((companies: any[]) => {
        const start = (page - 1) * pageSize;
        const end = start + pageSize;
        return companies.slice(start, end);
      })
    );
  }

  // Fetch details (change, marketCap, image) for specific symbols
  getCompaniesDetails(symbols: string[]): Observable<any[]> {
    const detailsRequests = symbols.map(symbol =>
      this.http.get<any[]>(`${this.quoteApiUrl}${symbol}?apikey=${this.apiKey}`).pipe(
        map((details: any[]) => ({
          symbol: symbol,
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
          symbol: symbol,
          image: profiles[0]?.image || 'default-image-url'
        }))
      )
    );

    return forkJoin([forkJoin(detailsRequests), forkJoin(profileRequests)]).pipe(
      map(([details, profiles]) => {
        return details.map((detail: any) => {
          const profile = profiles.find(p => p.symbol === detail.symbol);
          return {
            ...detail,
            image: profile?.image || 'default-image-url'
          };
        });
      })
    );
  }

  // Fetch profile images for specific symbols
  getCompaniesProfiles(symbols: string[]): Observable<any[]> {
    const requests = symbols.map(symbol =>
      this.http.get<any[]>(`${this.profileApiUrl}${symbol}?apikey=${this.apiKey}`).pipe(
        map((profiles: any[]) => ({
          symbol: symbol,
          image: profiles[0]?.image || 'default-image-url'
        }))
      )
    );
    return forkJoin(requests);
  }

  // Combine both methods with pagination
  // Combine all data fetching methods
  getAllCompanyData(page: number, pageSize: number): Observable<any[]> {
    return this.getCompaniesByPage(page, pageSize).pipe(
      switchMap((companiesList: any[]) => {
        const symbols = companiesList.map((company: any) => company.symbol);
        return forkJoin([
          this.getCompaniesDetails(symbols),
          this.getCompaniesProfiles(symbols),
          this.getComplianceStatus(symbols)  // Add compliance status fetching
        ]).pipe(
          map(([details, profiles, compliance]) => {
            return companiesList.map((company: any) => {
              const detail = details.find(d => d.symbol === company.symbol);
              const profile = profiles.find(p => p.symbol === company.symbol);
              const complianceStatus = compliance.find(c => c.symbol === company.symbol);
              return {
                ...company,
                change: detail?.change || 'N/A',
                marketCap: detail?.marketCap || 'N/A',
                image: profile?.image || 'default-image-url',
                complianceStatus: complianceStatus?.status || 'N/A'  // Add compliance status
              };
            });
          })
        );
      })
    );
  }
  // Fetch ETF companies
  getEftCompanies(page: number, pageSize: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.etfCompaniesUrl}`).pipe(
      map((companies: any[]) => {
        const start = (page - 1) * pageSize;
        const end = start + pageSize;
        return companies.slice(start, end);
      })
    );
  }
  // Combine both methods with pagination for ETF
  getAllEftData(page: number, pageSize: number): Observable<any[]> {
    return this.getEftCompanies(page, pageSize).pipe(
      switchMap((companiesList: any[]) => {
        const symbols = companiesList.map((company: any) => company.symbol);
        return forkJoin([
          this.getCompaniesDetails(symbols),
          this.getCompaniesProfiles(symbols),
          this.getComplianceStatus(symbols)  // Ajout de la conformité
        ]).pipe(
          map(([details, profiles, compliance]) => {
            return companiesList.map((company: any) => {
              const detail = details.find(d => d.symbol === company.symbol);
              const profile = profiles.find(p => p.symbol === company.symbol);
              const complianceStatus = compliance.find(c => c.symbol === company.symbol);
              return {
                ...company,
                change: detail?.change || 'N/A',
                marketCap: detail?.marketCap || 'N/A',
                image: profile?.image || 'default-image-url',
                complianceStatus: complianceStatus?.status || 'N/A'  // Ajout du statut de conformité
              };
            });
          })
        );
      })
    );
  }

}

