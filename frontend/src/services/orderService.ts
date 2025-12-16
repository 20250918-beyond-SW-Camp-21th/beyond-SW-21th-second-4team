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
   * userId는 @AuthenticationPrincipal로 백엔드에서 자동 추출됨
   */
  async createOrder(
    promotionId: number,
    quantity: number
  ): Promise<OrderResponse> {
    const request: CreateOrderRequest = { promotionId, quantity };
    const response = await api.post<ApiResult<OrderResponse>>(
      `${API_ENDPOINTS.ORDERS}/`,
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
