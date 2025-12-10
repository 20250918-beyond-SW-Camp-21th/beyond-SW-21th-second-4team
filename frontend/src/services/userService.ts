import { api } from './api';
import { API_ENDPOINTS } from '../config/api';
import { handleApiResponse } from '../types/api';
import type { ApiResult } from '../types/api';
import type { SignInRequest, SignInResponse, SignUpRequest, MyPageResponse } from '../types/user';
import type { Order } from '../types/order';

export const userService = {
  /**
   * Sign in user
   */
  async signIn(email: string, password: string): Promise<SignInResponse> {
    const request: SignInRequest = { email, password };
    const response = await api.post<ApiResult<SignInResponse>>(
      API_ENDPOINTS.USER_SIGNIN,
      request
    );
    return handleApiResponse(response.data);
  },

  /**
   * Sign out user
   * userId는 JWT 토큰에서 자동 추출됨
   */
  async signOut(): Promise<void> {
    const response = await api.post<ApiResult<void>>(
      API_ENDPOINTS.USER_SIGNOUT
    );
    return handleApiResponse(response.data);
  },

  /**
   * Sign up new user
   */
  async signUp(email: string, password: string, name: string): Promise<void> {
    const request: SignUpRequest = { email, password, name };
    const response = await api.post<ApiResult<void>>(
      API_ENDPOINTS.USER_SIGNUP,
      request
    );
    return handleApiResponse(response.data);
  },

  /**
   * Get user profile (MyPageResponse from backend)
   * userId는 JWT 토큰에서 자동 추출됨
   */
  async getUserProfile(): Promise<MyPageResponse> {
    const response = await api.get<ApiResult<MyPageResponse>>(API_ENDPOINTS.USER_ME);
    return handleApiResponse(response.data);
  },

  /**
   * Get user order history
   * userId는 JWT 토큰에서 자동 추출됨
   */
  async getOrderHistory(): Promise<Order[]> {
    const response = await api.get<ApiResult<Order[]>>(API_ENDPOINTS.USER_ORDERS);
    return handleApiResponse(response.data);
  },
};
