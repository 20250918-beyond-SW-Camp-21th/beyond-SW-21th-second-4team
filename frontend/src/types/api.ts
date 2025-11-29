// API Response Types
export interface ApiResult<T> {
  resultType: 'SUCCESS' | 'ERROR';
  data: T | null;
  error: ErrorMessage | null;
}

export interface ErrorMessage {
  code: string;
  message: string;
}

// Helper function to handle API responses
export function handleApiResponse<T>(response: ApiResult<T>): T {
  if (response.resultType === 'SUCCESS' && response.data !== null) {
    return response.data;
  } else {
    throw new Error(response.error?.message || 'API Error');
  }
}
