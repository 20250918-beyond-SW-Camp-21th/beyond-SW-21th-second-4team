// API Configuration
export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api/v1';

export const API_ENDPOINTS = {
  // Queue
  QUEUE_ENTER: '/queue',
  QUEUE_STATUS: '/queue',

  // User
  USER_SIGNIN: '/users/signIn',
  USER_SIGNOUT: '/users/signOut',
  USER_SIGNUP: '/users/signUp',
  USER_ME: '/users/me',
  USER_ORDERS: '/users/me/orders',

  // Promotion
  PROMOTIONS: '/promotions',
  PROMOTION_DETAIL: '/promotions',
  PROMOTION_BY_STATUS: '/promotions',
  PROMOTION_CREATE: '/promotions',
  PROMOTION_UPDATE: '/promotions',
  PROMOTION_UPDATE_STATUS: '/promotions',
  PROMOTION_DELETE: '/promotions',

  // Order
  ORDERS: '/orders',

  // Admin
  ADMIN_CREATE: '/admins',
  ADMIN_PRODUCTS: '/admins',
  ADMIN_PRODUCT_UPDATE: '/admins',
  ADMIN_PRODUCT_DELETE: '/admins',
} as const;
