import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { SignupComponent } from "./signup/signup.component";
import { MyportfolioComponent } from "./myportfolio/myportfolio.component";
import { StockScreenerComponent } from "./stock-screener/stock-screener.component";
import { WatchlistComponent } from "./watchlist/watchlist.component";
import {AccueilComponent} from "./accueil/accueil.component";
import {RequestedstocksComponent} from "./requestedstocks/requestedstocks.component";
import {SupportComponent} from "./support/support.component";
import {EtfscreenerComponent} from "./etfscreener/etfscreener.component";
import {DetailsComponent} from "./overview/details/details.component";
import {NewsComponent} from "./overview/news/news.component";
import {ComplianceComponent} from "./overview/compliance/compliance.component";
import {UserprofileComponent} from "./userprofile/userprofile.component";
import {ProfileComponent} from "./overview/profile/profile.component";
import {AboutComponent} from "./about/about.component";
import {OverviewComponent} from "./overview/overview.component";


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'myportfolio', component: MyportfolioComponent },
  { path: 'stockscreener', component: StockScreenerComponent },
  { path: 'watchlist', component: WatchlistComponent },
  { path: 'accueil', component: AccueilComponent },
  { path: 'userprofile', component: UserprofileComponent },
  { path: 'userprofile/:id', component: UserprofileComponent },
  { path: 'requestedstocks', component: RequestedstocksComponent },
  { path: 'support', component: SupportComponent },
  { path: 'etfscreener', component: EtfscreenerComponent },
  { path: 'overview/:symbol', component: OverviewComponent,
    children: [
      { path: 'details', component: DetailsComponent},
      { path: 'compliance', component: ComplianceComponent},
      { path: 'news', component: NewsComponent},
      { path: 'profile', component: ProfileComponent}
    ]
  },
  { path: 'about', component: AboutComponent },
  { path: '**', redirectTo: '' },


  // { path: 'search/:term', component: SearchResultsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
