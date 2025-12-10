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
   * Note: userId는 request body에 포함 (백엔드 OrderRequest 요구사항)
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
   * userId는 JWT 토큰에서 자동 추출됨
   */
  async getUserOrders(): Promise<OrderDetailResponse> {
    const response = await api.get<ApiResult<OrderDetailResponse>>(
      API_ENDPOINTS.USER_ORDERS
    );
    return handleApiResponse(response.data);
  },
};
