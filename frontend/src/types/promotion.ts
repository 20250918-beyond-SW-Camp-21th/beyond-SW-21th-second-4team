// Promotion Types (백엔드 PromotionResponse 스펙 기준)
export interface Promotion {
  // 백엔드 PromotionResponse 필드
  id: number;
  company?: string;
  productName: string;
  originalPrice: number;
  salePrice: number;
  discountRate: number;
  totalQuantity: number;
  startTime: string;
  endTime: string;
  productImageUrl?: string;
  
  // 프론트엔드 호환성을 위한 별칭 (optional)
  productImage?: string;     // productImageUrl의 별칭
  
  // 백엔드에서 추가로 제공할 수 있는 필드 (optional)
  adminId?: number;
  productId?: number;
  promotionStatus?: PromotionStatus;
  soldQuantity?: number;
}

// 백엔드 Enum: SCHEDULER, ACTIVE, ENDED
export type PromotionStatus = 'SCHEDULER' | 'ACTIVE' | 'ENDED';

// 백엔드 PromotionListResponse (상태별 조회 응답)
export interface PromotionListResponse {
  promotions: Promotion[];
}

// 백엔드 PromotionRequest (POST/PUT)
export interface PromotionRequest {
  adminId?: number;
  productId: number;
  discountRate: number;
  startTime: string;        // LocalDateTime
  endTime: string;          // LocalDateTime
  totalQuantity: number;
}
