import { api } from './api';
import { API_ENDPOINTS } from '../config/api';
import { handleApiResponse } from '../types/api';
import type { ApiResult } from '../types/api';
import type { Promotion, PromotionStatus, PromotionRequest } from '../types/promotion';

export const promotionService = {
  /**
   * Get all promotions
   */
  async getPromotions(): Promise<Promotion[]> {
    const response = await api.get<ApiResult<Promotion[]>>(
      API_ENDPOINTS.PROMOTIONS
    );
    return handleApiResponse(response.data);
  },

  /**
   * Get promotions by status
   */
  async getPromotionsByStatus(status: PromotionStatus): Promise<Promotion[]> {
    const response = await api.get<ApiResult<Promotion[]>>(
      `${API_ENDPOINTS.PROMOTION_BY_STATUS}/${status}`
    );
    return handleApiResponse(response.data);
  },

  /**
   * Get promotion details by ID
   */
  async getPromotionById(id: number): Promise<Promotion> {
    const response = await api.get<ApiResult<Promotion>>(
      `${API_ENDPOINTS.PROMOTION_DETAIL}/${id}`
    );
    return handleApiResponse(response.data);
  },

  /**
   * Create new promotion (Admin only)
   */
  async createPromotion(request: PromotionRequest): Promise<void> {
    const response = await api.post<ApiResult<void>>(
      API_ENDPOINTS.PROMOTION_CREATE,
      request
    );
    return handleApiResponse(response.data);
  },

  /**
   * Update promotion (Admin only)
   */
  async updatePromotion(id: number, request: PromotionRequest): Promise<void> {
    const response = await api.put<ApiResult<void>>(
      `${API_ENDPOINTS.PROMOTION_UPDATE}/${id}`,
      request
    );
    return handleApiResponse(response.data);
  },

  /**
   * Update promotion status (Admin only)
   */
  async updatePromotionStatus(id: number): Promise<void> {
    const response = await api.put<ApiResult<void>>(
      API_ENDPOINTS.PROMOTION_UPDATE_STATUS,
      null,
      {
        params: { id },
      }
    );
    return handleApiResponse(response.data);
  },

  /**
   * Delete promotion (Admin only)
   */
  async deletePromotion(id: number): Promise<void> {
    const response = await api.delete<ApiResult<void>>(
      `${API_ENDPOINTS.PROMOTION_DELETE}/${id}`
    );
    return handleApiResponse(response.data);
  },
};
