// API Configuration
export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8000/api/v1';

export const API_ENDPOINTS = {
  // Queue (Gateway: /api/v1/queues/**)
  QUEUE_ENTER: '/queues',
  QUEUE_STATUS: '/queues',

  // User - Account Service (Gateway: /api/v1/account/**)
  USER_SIGNIN: '/account/users/signIn',
  USER_SIGNOUT: '/account/users/signOut',
  USER_SIGNUP: '/account/users/signUp',
  USER_ME: '/account/users/me',
  USER_ORDERS: '/account/users/me/orders',

  // Promotion (Gateway: /api/v1/promotions/**)
  PROMOTIONS: '/promotions',
  PROMOTION_DETAIL: '/promotions',
  PROMOTION_BY_STATUS: '/promotions',
  PROMOTION_CREATE: '/promotions',
  PROMOTION_UPDATE: '/promotions',
  PROMOTION_UPDATE_STATUS: '/promotions',
  PROMOTION_DELETE: '/promotions',

  // Order (Gateway: /api/v1/orders/**)
  ORDERS: '/orders',

  // Admin (Gateway: /api/v1/admins/**)
  ADMIN_CREATE: '/admins',
  ADMIN_PRODUCTS: '/admins',
  ADMIN_PRODUCT_UPDATE: '/admins',
  ADMIN_PRODUCT_DELETE: '/admins',
} as const;
