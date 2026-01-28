import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  title = 'About Us';
  subtitle = 'Learn more about our company, our values, and our mission.';

  companyOverview = {
    imageUrl: 'https://fiqhcouncil.org/wp-content/uploads/2024/01/649afcb5867d960019d93090.webp',
    heading: 'Who We Are',
    description1: `Our company was founded with the mission to provide the best financial solutions for people looking to invest in stocks and ETFs that are aligned with their values.`,
    description2: `We strive to ensure transparency, accessibility, and ethical finance for all. We believe in empowering individuals by giving them the tools to make informed financial decisions that match their goals and principles.`
  };

  coreValues = [
    {
      icon: 'fas fa-handshake',
      title: 'Integrity',
      description: `We operate with honesty and transparency, ensuring that every decision we make benefits our clients and partners.`
    },
    {
      icon: 'fas fa-balance-scale',
      title: 'Ethics',
      description: `Our commitment to ethical finance drives everything we do, from the products we offer to how we engage with our clients.`
    },
    {
      icon: 'fas fa-users',
      title: 'Collaboration',
      description: `We believe in working together, leveraging diverse perspectives to create solutions that benefit everyone involved.`
    }
  ];

  mission = `Our mission is to revolutionize ethical investing by providing innovative tools and education to empower individuals to grow their wealth in a way that aligns with their values.`;
  vision = 'Most Muslims still do not have access to good Islamic Financial education, including knowledge of investments and capital markets. Moreover, observant Muslims tend to shy away from financial markets because they do not want to invest in forbidden (haram) assets unintentionally. As a result, most Muslims are not enjoying the same financial rewards that non-Muslims are reaping by participating in the financial markets. It does not have to be the case. To address this issue, we are building an easy-to-use Halal trading platform where Muslim investors can learn how to invest, do research, and execute trades themselves, all in one platform. Thus, by using our platform, Muslim investors will have the opportunity to educate themselves and become financially independent the halal way.'



  teamMembers = [
    { name: 'ISSAM OUAISSA', role: 'Developer', imageUrl: 'https://via.placeholder.com/150' },
    { name: 'MOHAMMED EL BAKKALI', role: 'Developer', imageUrl: 'https://via.placeholder.com/150' },
    { name: 'HAMZA ABID', role: 'Developer', imageUrl: 'https://via.placeholder.com/150' },
    { name: 'KARIM MAHABA', role: 'Developer', imageUrl: 'https://via.placeholder.com/150' }
  ];

  constructor() { }

  ngOnInit(): void {
  }






  footerSections = [
    {
      title: 'Company',
      links: [
        {name: 'Community', url: '/community'},
        {name: 'Contact', url: '/contact'},
        {name: 'About', url: '/about'},
        {name: 'Blog', url: '/blog'}
      ]
    },
    {
      title: 'Resources',
      links: [
        {name: 'Zakat Calculator', url: '/zakat-calculator'},
        {name: 'Help Desk', url: '/help-desk'},
        {name: 'Learn', url: '/learn'},
        {name: 'API', url: '/api'}
      ]
    },
    {
      title: 'Legal',
      links: [
        {name: 'Privacy Policy', url: '/privacy-policy'},
        {name: 'Terms of Use', url: '/terms-of-use'}
      ]
    }
  ];

}
