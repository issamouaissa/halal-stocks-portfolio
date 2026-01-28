import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  mainHeading: string = 'Taking the guesswork out of Halal Investing';
  subHeading: string = '';
  description: string = 'HalalInv makes halal investing easy by helping you build & monitor a shariah compliant investment portfolio.';
  searchQuery: string = '';
  imageUrl = 'https://cdn.prod.website-files.com/6401578edcad599a8823c1be/64c90b06ad1c8a6f730bd647_zoya-app-iphone.png';
  altImg = 'Smartphones displaying crypto trading app';
  galleryTitle: string = 'Your Halal Investing Copilot';
  galleryDescription: string = 'HalalInv is a comprehensive, all-in-one platform.';
  headerTitle: string = 'Trusted by 200,000+ Investors';
  headerDescription: string = 'We\'ve received countless unsolicited messages from customers with stories of how HalalInv has helped them.';

  images = [
    { src: 'https://cdn.prod.website-files.com/6401578edcad599a8823c1be/64c938fb9b6ae3cb2054b6df_zoya-portfolio-tracker.png', alt: 'Portfolio Tracker', title: 'Portfolio Tracker', description: 'Connect and sync your existing brokerage accounts to track your portfolio and monitor your holdings.' },
    { src: 'https://cdn.prod.website-files.com/6401578edcad599a8823c1be/64c96eda817efd903c57fb5a_zoya-trading.png', alt: 'Buy & Sell Stocks', title: 'Buy & Sell Stocks', description: 'Trade stocks using your existing broker without ever having to leave Zoya.' },
    { src: 'https://cdn.prod.website-files.com/6401578edcad599a8823c1be/64c948201abcaf97c445793d_zoya-shariah-compliance-screener.png', alt: 'Reliable Shariah Compliance Reports', title: 'Reliable Shariah Compliance Reports', description: 'Access detailed and up-to-date shariah compliance reports for over 30,000 global stocks.' },
    { src: 'https://cdn.prod.website-files.com/6401578edcad599a8823c1be/64c96a222ec452771bd82683_zoya-etf-mutual-fund-screener.png', alt: 'ETF & Mutual Fund Screener', title: 'ETF & Mutual Fund Screener', description: 'View and filter the underlying holdings of thousands of ETFs and mutual funds based on their shariah compliance status.' },
    { src: 'https://cdn.prod.website-files.com/6401578edcad599a8823c1be/64c937d83d40c7173bd39b42_zoya-comliance-alerts.png', alt: 'Smart Alerts', title: 'Smart Alerts', description: 'We\'ll monitor your stocks and alert you of any changes in shariah compliance.' },
    { src: 'https://cdn.prod.website-files.com/6401578edcad599a8823c1be/64c933b1835a2961d6e7aac4_zoya-zakat-calculator.png', alt: 'Calculate & Donate Zakat', title: 'Calculate & Donate Zakat', description: 'Calculate zakat due on your investments with precision and donate to your favorite charities.' },
  ];

  imageRows: any[] = [];

  testimonials = [
    { rating: 5, review: 'Love using this app, very user friendly and easy to navigate - would 100% recommend to all Muslims looking to invest responsibly and according to the deen.', author: 'MB2684' , source: 'iOS App Store'},
    { rating: 5, review: 'As a socially conscious investor seeking to align my financial endeavors with my ethical values, I have been on the lookout for a mobile app that seamlessly combines convenience with religious principles. My search ended when I discovered HalalInv, the ultimate solution for those who prioritize Shariah-compliant investing.', author: 'Qaisar Majeed' ,source: 'iOS App Store'},
    { rating: 5, review: 'Alhamdullilah, my sheikh told me about this app, and I was quite hesitant to invest my money into the market, not only cause it was volatile, but because I was afraid I would be earning haram. This app has eliminated that fear, especially as a new “investor”.', author: 'ozjawan' ,source: 'iOS App Store'},
    { rating: 5, review: 'HalalInv has been very helpful for me from the standpoint of verification of Shariah compliant stocks. I truly appreciate the efforts of the team for doing all the necessary verification and educating us of all the Shariah Compliant stocks that we can invest in. I was able to get rid of all non-shariah compliant stocks and Alhamdulillah my portfolio is now all Shariah compliant stocks.', author: 'Bubba Senior', source: 'iOS App Store' },
    { rating: 5, review: 'HalalInv is so helpful even if you are completely lost when it comes to halal investing. The app may look intimidating when you first open it but they will answer your personal questions and make sure to help you get on the right track.', author: 'Msb079', source: 'iOS App Store' },
    { rating: 5, review: 'This app is useful! This app makes halal investing accessible! Before HalalInv I I was lost and wondering how I can get access to halal stocks! Thank you Zoya!', author: 'Hafid1978', source: 'iOS App Store' },
    { rating: 5, review: 'Very useful app for those who are looking for halal stock investing options. I was very glad to see that they added ETF and mutual fund screening as well.', author: 'Omar Tuffaha', source: 'iOS App Store' },
    { rating: 5, review: 'For the first time, I can access halal stocks and monitor their compliance with ease. Some key features I love about this app are the email updates that I receive to notify me that one of my watchlist stocks have become non-compliant.', author: 'Princeabobwa', source: 'iOS App Store' },
    { rating: 5, review: 'Amazing app with excellent customer service. Always willing to answer questions and open to feedback. A much needed service has been filled with this app.', author: 'Mohammed K. Beyad', source: 'Google Play Store' },
    { rating: 5, review: 'HalalInv is the only app I know of to check sharia compliance of stocks, mutual funds and ETF’s. They also have an active community where you can ask/answer investment related questions.', author: 'Abooismail', source: 'iOS App Store' },
    { rating: 5, review: 'This app is absolutely brilliant, easy to navigate, great layout, and very useful features. It’s really easy to understand for each different stock what does into the compliant/non-complaint. I also especially love the ‘compliant’ alternative option.', author: 'Muhammad Effendi', source: 'iOS App Store' },
    { rating: 5, review: 'It\'s really nice that the app tells you exactly how each rating was decided since sometimes there\'s a lot of ambiguity at play. Saad, the developer is also very responsive whenever there are issues. Only good experiences so far.', author: 'Match stick lover', source: 'iOS App Store' },
  ];


  constructor(private router: Router) {
    this.arrangeImagesInRows();
  }

  arrangeImagesInRows() {
    const itemsPerRow = 2;
    for (let i = 0; i < this.images.length; i += itemsPerRow) {
      this.imageRows.push(this.images.slice(i, i + itemsPerRow));
    }
  }

  /*logIn(): void {
    this.router.navigate(['/login']);
    // Ajouter une logique de connexion ici si nécessaire
  }

  getStarted(): void {
    this.router.navigate(['/signup']);
    // Ajouter une logique pour démarrer ici si nécessaire
  }*/

  onSearch(): void {
    if (this.searchQuery.trim()) {
      console.log('Search Query:', this.searchQuery);
      // Ajouter une logique de recherche ici
    } else {
      console.log('Search query is empty');
    }
  }


  footerSections = [
    {
      title: 'Company',
      links: [
        { name: 'Community', url: '/community' },
        { name: 'Contact', url: '/contact' },
        { name: 'About', url: '/about' },
        { name: 'Blog', url: '/blog' }
      ]
    },
    {
      title: 'Resources',
      links: [
        { name: 'Zakat Calculator', url: '/zakat-calculator' },
        { name: 'Help Desk', url: '/help-desk' },
        { name: 'Learn', url: '/learn' },
        { name: 'API', url: '/api' }
      ]
    },
    {
      title: 'Legal',
      links: [
        { name: 'Privacy Policy', url: '/privacy-policy' },
        { name: 'Terms of Use', url: '/terms-of-use' }
      ]
    }
  ];
}
