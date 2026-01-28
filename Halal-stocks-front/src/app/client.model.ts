// client.model.ts
export interface Client {
  clientid: number;
  clientname: string;
  email: string;
  password: string; // Optional, as we don’t use it for display
}
