import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { MyportfolioComponent } from './myportfolio/myportfolio.component';
import { StockScreenerComponent } from './stock-screener/stock-screener.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import { AppRoutingModule } from "./app-routing.module";
import {HttpClientModule, provideHttpClient, withFetch} from "@angular/common/http";
import { AuthService } from "./auth.service";
import { AccueilComponent } from './accueil/accueil.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NavaccueilComponent } from './navaccueil/navaccueil.component';
import { RequestedstocksComponent } from './requestedstocks/requestedstocks.component';
import { SupportComponent } from './support/support.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { EtfscreenerComponent } from './etfscreener/etfscreener.component';
import { ComplianceComponent } from './overview/compliance/compliance.component';
import { NewsComponent } from './overview/news/news.component';
import { DetailsComponent } from './overview/details/details.component';
import { UserprofileComponent } from './userprofile/userprofile.component';
import {ProfileComponent} from "./overview/profile/profile.component";
import { AboutComponent } from './about/about.component';
import { OverviewComponent } from './overview/overview.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    MyportfolioComponent,
    StockScreenerComponent,
    WatchlistComponent,
    AccueilComponent,
    NavbarComponent,
    NavaccueilComponent,
    UserprofileComponent,
    RequestedstocksComponent,
    SupportComponent,
    EtfscreenerComponent,
    ComplianceComponent,
    NewsComponent,
    DetailsComponent,
    ProfileComponent,
    AboutComponent,
    OverviewComponent,
  ],
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,
        HttpClientModule,
        ReactiveFormsModule,
        NgbModule,
    ],
  providers: [AuthService,
    provideHttpClient(withFetch())],
  bootstrap: [AppComponent]
})
export class AppModule { }
