// Promotion Types
export interface Promotion {
  id: number;
  productId: number;
  productName: string;
  productImage?: string;
  originalPrice: number;
  promotionPrice: number;
  discountRate: number;
  totalQuantity: number;
  remainingQuantity: number;
  startTime: string;
  endTime: string;
  status: PromotionStatus;
  createdAt: string;
  updatedAt: string;
}

export type PromotionStatus = 'ACTIVE' | 'INACTIVE' | 'ENDED';

export interface PromotionListResponse {
  promotions: Promotion[];
  total: number;
}
