import { api } from './api';
import { API_ENDPOINTS } from '../config/api';
import { handleApiResponse } from '../types/api';
import type { ApiResult } from '../types/api';
import type { Promotion, PromotionStatus, PromotionRequest, PromotionListResponse } from '../types/promotion';

export const promotionService = {
  /**
   * Get all promotions
   * Gateway: /api/v1/promotions -> promotion-service /
   */
  async getPromotions(): Promise<Promotion[]> {
    const response = await api.get<ApiResult<Promotion[]>>(
      API_ENDPOINTS.PROMOTIONS
    );
    return handleApiResponse(response.data);
  },

  /**
   * Get promotions by status
   * Gateway: /api/v1/promotions/status/{status} -> promotion-service /status/{status}
   */
  async getPromotionsByStatus(status: PromotionStatus): Promise<PromotionListResponse> {
    const response = await api.get<ApiResult<PromotionListResponse>>(
      `${API_ENDPOINTS.PROMOTIONS}/status/${status}`
    );
    return handleApiResponse(response.data);
  },

  /**
   * Get promotion details by ID
   * Gateway: /api/v1/promotions/{id} -> promotion-service /{id}
   */
  async getPromotionById(id: number): Promise<Promotion> {
    const response = await api.get<ApiResult<Promotion>>(
      `${API_ENDPOINTS.PROMOTIONS}/${id}`
    );
    return handleApiResponse(response.data);
  },

  /**
   * Create new promotion (Admin only)
   */
  async createPromotion(request: PromotionRequest): Promise<void> {
    const response = await api.post<ApiResult<void>>(
      API_ENDPOINTS.PROMOTIONS,
      request
    );
    return handleApiResponse(response.data);
  },

  /**
   * Update promotion (Admin only)
   */
  async updatePromotion(id: number, request: PromotionRequest): Promise<void> {
    const response = await api.put<ApiResult<void>>(
      `${API_ENDPOINTS.PROMOTIONS}/${id}`,
      request
    );
    return handleApiResponse(response.data);
  },

  /**
   * Delete promotion (Admin only)
   */
  async deletePromotion(id: number): Promise<void> {
    const response = await api.delete<ApiResult<void>>(
      `${API_ENDPOINTS.PROMOTIONS}/${id}`
    );
    return handleApiResponse(response.data);
  },
};
