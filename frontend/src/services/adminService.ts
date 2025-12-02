import { api } from './api';
import { API_ENDPOINTS } from '../config/api';
import { handleApiResponse } from '../types/api';
import type { ApiResult } from '../types/api';
import type {
  AdminRequest,
  AdminResponse,
  ProductRequest,
  ProductResponse,
  ProductListResponse,
} from '../types/admin';

export const adminService = {
  /**
   * Create admin
   */
  async createAdmin(request: AdminRequest): Promise<AdminResponse> {
    const response = await api.post<ApiResult<AdminResponse>>(
      API_ENDPOINTS.ADMIN_CREATE,
      request
    );
    return handleApiResponse(response.data);
  },

  /**
   * Get products by admin ID
   */
  async getProductsByAdminId(adminId: number): Promise<ProductListResponse> {
    const response = await api.get<ApiResult<ProductListResponse>>(
      `${API_ENDPOINTS.ADMIN_PRODUCTS}/${adminId}/products`
    );
    return handleApiResponse(response.data);
  },

  /**
   * Update product by admin
   */
  async updateProduct(
    adminId: number,
    productId: number,
    request: ProductRequest
  ): Promise<ProductResponse> {
    const response = await api.put<ApiResult<ProductResponse>>(
      `${API_ENDPOINTS.ADMIN_PRODUCT_UPDATE}/${adminId}/products/${productId}`,
      request
    );
    return handleApiResponse(response.data);
  },

  /**
   * Delete product by admin
   */
  async deleteProduct(adminId: number, productId: number): Promise<void> {
    const response = await api.delete<ApiResult<void>>(
      `${API_ENDPOINTS.ADMIN_PRODUCT_DELETE}/${adminId}/products/${productId}`
    );
    return handleApiResponse(response.data);
  },
};
