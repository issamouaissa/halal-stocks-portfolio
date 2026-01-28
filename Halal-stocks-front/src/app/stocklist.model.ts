export interface StockList {
  symbol: string;
  name: string;
  price: number;
  change?: number;
  marketCap?: number;
  image?: string; // Ajouté pour inclure l'image
  complianceStatus: string;

}
