import { api } from './api';
import { API_ENDPOINTS } from '../config/api';
import { handleApiResponse } from '../types/api';
import type { ApiResult } from '../types/api';
import type {
  CreateOrderRequest,
  OrderResponse,
  OrderDetailResponse
} from '../types/order';

export const orderService = {
  /**
   * Create a new order
   * 백엔드 응답: { OrderId: number, quantity: number }
   */
  async createOrder(
    userId: number,
    promotionId: number,
    quantity: number
  ): Promise<OrderResponse> {
    const request: CreateOrderRequest = { promotionId, quantity, userId };
    const response = await api.post<ApiResult<OrderResponse>>(
      API_ENDPOINTS.ORDERS,
      request
    );
    return handleApiResponse(response.data);
  },

  /**
   * Get user's order history
   * 백엔드 응답: OrderDetailResponse { myPageOrderResponseList: [...] }
   */
  async getUserOrders(userId: number): Promise<OrderDetailResponse> {
    const response = await api.get<ApiResult<OrderDetailResponse>>(
      `${API_ENDPOINTS.USER_ORDERS}`,
      {
        params: { userId },
      }
    );
    return handleApiResponse(response.data);
  },
};
