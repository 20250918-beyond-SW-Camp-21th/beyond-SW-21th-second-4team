// Admin Types
export interface Admin {
  id: number;
  email: string;
  name: string;
  createdAt: string;
  updatedAt: string;
}

export interface AdminRequest {
  email: string;
  password: string;
  name: string;
}

export interface AdminResponse {
  adminId: number;
  email: string;
  name: string;
}

export interface Product {
  id: number;
  name: string;
  description?: string;
  price: number;
  stock: number;
  imageUrl?: string;
  createdAt: string;
  updatedAt: string;
}

export interface ProductRequest {
  name: string;
  description?: string;
  price: number;
  stock: number;
  imageUrl?: string;
}

export interface ProductResponse {
  productId: number;
  name: string;
  description?: string;
  price: number;
  stock: number;
  imageUrl?: string;
}

export interface ProductListResponse {
  products: ProductResponse[];
  total: number;
}
