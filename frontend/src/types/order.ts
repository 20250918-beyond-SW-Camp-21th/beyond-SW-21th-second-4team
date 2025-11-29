// Order Types
export interface Order {
  id: number;
  userId: number;
  promotionId: number;
  productName: string;
  quantity: number;
  totalPrice: number;
  status: OrderStatus;
  createdAt: string;
  updatedAt: string;
}

export type OrderStatus = 'DONE' | 'CANCELLED';

export interface CreateOrderRequest {
  userId: number;
  promotionId: number;
  quantity: number;
}

export interface OrderDetailResponse {
  order: Order;
  details: OrderDetail[];
}

export interface OrderDetail {
  id: number;
  orderId: number;
  productId: number;
  productName: string;
  quantity: number;
  price: number;
}
