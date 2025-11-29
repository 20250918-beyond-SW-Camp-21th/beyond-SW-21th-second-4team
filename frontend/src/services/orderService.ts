import { api } from './api';
import { API_ENDPOINTS } from '../config/api';
import { ApiResult, handleApiResponse } from '../types/api';
import { Order, CreateOrderRequest } from '../types/order';

export const orderService = {
  /**
   * Create a new order
   */
  async createOrder(
    userId: number,
    promotionId: number,
    quantity: number
  ): Promise<Order> {
    const request: CreateOrderRequest = { userId, promotionId, quantity };
    const response = await api.post<ApiResult<Order>>(
      API_ENDPOINTS.ORDERS,
      request
    );
    return handleApiResponse(response.data);
  },
};
