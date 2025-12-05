import React from 'react';
import { Button } from './Button';

interface ErrorMessageProps {
  title?: string;
  message: string;
  onRetry?: () => void;
  onBack?: () => void;
  fullScreen?: boolean;
}

export const ErrorMessage: React.FC<ErrorMessageProps> = ({
  title = '오류가 발생했습니다',
  message,
  onRetry,
  onBack,
  fullScreen = false,
}) => {
  const containerClass = fullScreen
    ? 'min-h-screen flex items-center justify-center bg-bg-gray px-4'
    : '';

  return (
    <div className={containerClass}>
      <div className="bg-white border border-sale-red rounded-lg p-8 max-w-md w-full text-center">
        <div className="text-5xl mb-4">⚠️</div>
        <h2 className="text-2xl font-bold text-text-primary mb-2">{title}</h2>
        <p className="text-text-secondary mb-6 whitespace-pre-line">{message}</p>
        <div className="flex gap-3 justify-center">
          {onRetry && (
            <Button variant="primary" onClick={onRetry}>
              다시 시도
            </Button>
          )}
          {onBack && (
            <Button variant="outline" onClick={onBack}>
              돌아가기
            </Button>
          )}
        </div>
      </div>
    </div>
  );
};
