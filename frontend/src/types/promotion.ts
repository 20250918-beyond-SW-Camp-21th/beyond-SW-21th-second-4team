// Promotion Types (백엔드 PromotionResponse에 맞춤)
export interface Promotion {
  id: number;
  adminId: number;
  productId: number;
  discountRate: number;
  totalQuantity: number;
  startTime: string;
  endTime: string;

  // 추가 필드 (옵셔널 - 프론트에서 계산하거나 표시용)
  productName?: string;
  productImage?: string;
  originalPrice?: number;
  salePrice?: number;
  soldQuantity?: number;
  promotionStatus?: PromotionStatus;
}

export type PromotionStatus = 'ACTIVE' | 'INACTIVE' | 'ENDED';

export interface PromotionListResponse {
  promotions: Promotion[];
  total: number;
}
