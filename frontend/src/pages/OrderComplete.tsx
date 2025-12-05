import React, { useEffect, useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { Button } from '../components/common';

interface OrderInfo {
  orderId: number;
  productName: string;
  quantity: number;
  totalPrice: number;
  orderDate: string;
}

export const OrderComplete: React.FC = () => {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const [orderInfo, setOrderInfo] = useState<OrderInfo | null>(null);

  useEffect(() => {
    // URL 파라미터에서 주문 정보 가져오기
    const orderId = searchParams.get('orderId');
    const productName = searchParams.get('productName');
    const quantity = searchParams.get('quantity');
    const totalPrice = searchParams.get('totalPrice');

    if (orderId && productName && quantity && totalPrice) {
      setOrderInfo({
        orderId: parseInt(orderId),
        productName: decodeURIComponent(productName),
        quantity: parseInt(quantity),
        totalPrice: parseInt(totalPrice),
        orderDate: new Date().toISOString(),
      });
    }
  }, [searchParams]);

  if (!orderInfo) {
    return (
      <div className="max-w-2xl mx-auto px-4 py-8">
        <div className="text-center">
          <p className="text-text-meta mb-4">주문 정보를 불러올 수 없습니다.</p>
          <Button onClick={() => navigate('/promotions')}>
            타임딜 보러가기
          </Button>
        </div>
      </div>
    );
  }

  return (
    <div className="max-w-2xl mx-auto px-4 py-8">
      {/* Success Animation */}
      <div className="text-center mb-8">
        <div className="inline-flex items-center justify-center w-24 h-24 bg-success-green rounded-full mb-4 animate-bounce">
          <svg
            className="w-12 h-12 text-white"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth={3}
              d="M5 13l4 4L19 7"
            />
          </svg>
        </div>
        <h1 className="text-3xl font-bold text-text-primary mb-2">
          주문이 완료되었습니다!
        </h1>
        <p className="text-text-secondary">
          타임딜 상품을 성공적으로 구매하셨습니다.
        </p>
      </div>

      {/* Order Info Card */}
      <div className="bg-white border border-border-default rounded-lg overflow-hidden mb-6">
        {/* Header */}
        <div className="bg-bg-light px-6 py-4 border-b border-border-default">
          <h2 className="text-lg font-bold text-text-primary">주문 정보</h2>
        </div>

        {/* Body */}
        <div className="p-6 space-y-4">
          <div className="flex justify-between items-center pb-4 border-b border-border-default">
            <span className="text-text-meta">주문 번호</span>
            <span className="font-mono font-bold text-text-primary">
              #{orderInfo.orderId.toString().padStart(8, '0')}
            </span>
          </div>

          <div className="flex justify-between items-center pb-4 border-b border-border-default">
            <span className="text-text-meta">주문 일시</span>
            <span className="text-text-primary">
              {new Date(orderInfo.orderDate).toLocaleString('ko-KR')}
            </span>
          </div>

          <div className="flex justify-between items-center pb-4 border-b border-border-default">
            <span className="text-text-meta">상품명</span>
            <span className="text-text-primary font-bold">{orderInfo.productName}</span>
          </div>

          <div className="flex justify-between items-center pb-4 border-b border-border-default">
            <span className="text-text-meta">수량</span>
            <span className="text-text-primary">{orderInfo.quantity}개</span>
          </div>

          <div className="flex justify-between items-center pt-2">
            <span className="text-lg font-bold text-text-primary">결제 금액</span>
            <span className="text-2xl font-bold text-sale-red">
              {orderInfo.totalPrice.toLocaleString()}원
            </span>
          </div>
        </div>
      </div>

      {/* Delivery Info */}
      <div className="bg-primary-blue bg-opacity-5 border border-primary-blue border-opacity-30 rounded-lg p-6 mb-6">
        <div className="flex items-start gap-3">
          <div className="text-2xl">📦</div>
          <div>
            <h3 className="font-bold text-text-primary mb-2">배송 안내</h3>
            <ul className="text-sm text-text-secondary space-y-1">
              <li>• 영업일 기준 2-3일 내 배송됩니다</li>
              <li>• 배송 시작 시 알림을 보내드립니다</li>
              <li>• 배송 조회는 주문 내역에서 확인하실 수 있습니다</li>
            </ul>
          </div>
        </div>
      </div>

      {/* Additional Info */}
      <div className="bg-bg-light rounded-lg p-6 mb-6">
        <div className="flex items-start gap-3">
          <div className="text-2xl">💡</div>
          <div>
            <h3 className="font-bold text-text-primary mb-2">유의사항</h3>
            <ul className="text-sm text-text-secondary space-y-1">
              <li>• 주문 내역은 마이페이지에서 확인하실 수 있습니다</li>
              <li>• 타임딜 상품은 취소/환불이 제한될 수 있습니다</li>
              <li>• 문의사항은 고객센터로 연락해주세요</li>
            </ul>
          </div>
        </div>
      </div>

      {/* Action Buttons */}
      <div className="space-y-3">
        <Button
          variant="primary"
          fullWidth
          size="large"
          onClick={() => navigate('/orders')}
        >
          주문 내역 보기
        </Button>
        <Button
          variant="outline"
          fullWidth
          onClick={() => navigate('/promotions')}
        >
          타임딜 계속 보기
        </Button>
      </div>

      {/* Thank You Message */}
      <div className="mt-8 text-center">
        <p className="text-text-meta text-sm">
          저희 타임딜을 이용해 주셔서 감사합니다!
        </p>
      </div>
    </div>
  );
};
