import { useState, useEffect, useRef } from 'react';
import type { QueueResponse } from '../types/queue';
import { queueService } from '../services/queueService';

export const useQueue = (promotionId: number | null) => {
  const [queueData, setQueueData] = useState<QueueResponse | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const intervalRef = useRef<NodeJS.Timeout | null>(null);

  const checkQueueStatus = async () => {
    if (!promotionId) return;

    try {
      setError(null);
      const data = await queueService.checkStatus(promotionId);
      setQueueData(data);
    } catch (err: any) {
      setError(err.message || '큐 상태 확인에 실패했습니다.');
    }
  };

  const enterQueue = async () => {
    if (!promotionId) return;

    try {
      setLoading(true);
      setError(null);
      const data = await queueService.enterQueue(promotionId);
      setQueueData(data);
      // Start polling after entering queue
      startPolling();
    } catch (err: any) {
      setError(err.message || '큐 입장에 실패했습니다.');
    } finally {
      setLoading(false);
    }
  };

  const leaveQueue = async () => {
    if (!promotionId) return;

    try {
      setLoading(true);
      await queueService.leaveQueue(promotionId);
      setQueueData(null);
      stopPolling();
    } catch (err: any) {
      setError(err.message || '큐 나가기에 실패했습니다.');
    } finally {
      setLoading(false);
    }
  };

  const startPolling = () => {
    // Clear existing interval
    if (intervalRef.current) {
      clearInterval(intervalRef.current);
    }

    // Poll every 3 seconds
    intervalRef.current = setInterval(() => {
      checkQueueStatus();
    }, 3000);
  };

  const stopPolling = () => {
    if (intervalRef.current) {
      clearInterval(intervalRef.current);
      intervalRef.current = null;
    }
  };

  useEffect(() => {
    // Check status on mount if promotionId is provided
    if (promotionId) {
      checkQueueStatus();
    }

    return () => {
      stopPolling();
    };
  }, [promotionId]);

  useEffect(() => {
    // Stop polling if expired or not in queue
    if (queueData && queueData.status === 'EXPIRED') {
      stopPolling();
    }
  }, [queueData]);

  return {
    queueData,
    loading,
    error,
    enterQueue,
    leaveQueue,
    checkQueueStatus,
    startPolling,
    stopPolling,
  };
};
