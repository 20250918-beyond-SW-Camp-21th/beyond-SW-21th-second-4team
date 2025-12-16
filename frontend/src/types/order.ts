// Order Types (백엔드 API 스펙 기준)

// 백엔드 OrderRequest (POST /api/v1/orders)
// userId는 @AuthenticationPrincipal로 백엔드에서 자동 추출
export interface CreateOrderRequest {
  promotionId: number;
  quantity: number;
}

// 백엔드 OrderResponse (POST /api/v1/orders 응답)
export interface OrderResponse {
  OrderId: number;      // 백엔드는 대문자 O
  quantity: number;
}

// 프론트엔드용 확장 Order 타입 (주문 목록 표시용)
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

// 백엔드 MyPageOrderResponse (GET /api/v1/users/me/orders)
// Java record 필드명이 PromotionName (대문자 P)으로 되어있어 JSON도 대문자로 직렬화됨
export interface MyPageOrderResponse {
  orderId: number;
  image: string;
  PromotionName: string;    // Java record 필드명 그대로 (대문자 P)
  quantity: number;
  price: number;
  orderDate: string;        // LocalDateTime
}

// 백엔드 OrderDetailResponse (GET /api/v1/users/me/orders)
export interface OrderDetailResponse {
  myPageOrderResponseList: MyPageOrderResponse[];
}

export interface OrderDetail {
  id: number;
  orderId: number;
  productId: number;
  productName: string;
  quantity: number;
  price: number;
}
