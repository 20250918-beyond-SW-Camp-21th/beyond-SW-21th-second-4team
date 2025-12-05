import React from 'react';

interface LoadingSpinnerProps {
  size?: 'sm' | 'md' | 'lg';
  text?: string;
  fullScreen?: boolean;
}

export const LoadingSpinner: React.FC<LoadingSpinnerProps> = ({
  size = 'md',
  text = '로딩 중...',
  fullScreen = false,
}) => {
  const sizeClasses = {
    sm: 'h-8 w-8 border-2',
    md: 'h-12 w-12 border-2',
    lg: 'h-16 w-16 border-4',
  };

  const Container = fullScreen ? 'div' : React.Fragment;
  const containerProps = fullScreen
    ? { className: 'min-h-screen flex items-center justify-center bg-bg-gray' }
    : {};

  return (
    <Container {...containerProps}>
      <div className="text-center">
        <div
          className={`animate-spin rounded-full border-b-sale-red mx-auto mb-4 ${sizeClasses[size]}`}
          style={{ borderTopColor: 'transparent', borderRightColor: 'transparent', borderLeftColor: 'transparent' }}
        />
        {text && <p className="text-text-meta">{text}</p>}
      </div>
    </Container>
  );
};
