// Queue Types
export interface QueueRequest {
  timedealId: number;
  userId: number;
}

export interface QueueResponse {
  position: number;
  status: QueueStatus;
  waitTime?: number;
  proceedExpiryTime?: string;
}

export type QueueStatus = 'WAITING' | 'PROCEED' | 'EXPIRED';
