# Timedeal Frontend

React + TypeScript + Tailwind CSS 기반 타임딜 플랫폼 프론트엔드

## 기술 스택

- **React 18** - UI 라이브러리
- **TypeScript** - 타입 안정성
- **Vite** - 빌드 도구
- **Tailwind CSS** - 스타일링 (11번가 디자인 기반)
- **React Router** - 라우팅
- **Axios** - HTTP 클라이언트
- **date-fns** - 날짜/시간 처리

## 시작하기

### 1. 패키지 설치

```bash
npm install
```

### 2. 환경 변수 설정

`.env.example`을 복사하여 `.env` 파일 생성:

```bash
cp .env.example .env
```

### 3. 개발 서버 실행

```bash
npm run dev
```

개발 서버: http://localhost:5173

### 4. 백엔드 연동

백엔드 서버가 http://localhost:8080 에서 실행 중이어야 합니다.

```bash
# 백엔드 디렉토리에서
./gradlew bootRun
```

## 프로젝트 구조

```
src/
├── components/          # 재사용 가능한 UI 컴포넌트
│   ├── common/          # 공통 컴포넌트 (Button, Input 등)
│   ├── layout/          # 레이아웃 컴포넌트 (Header, Footer)
│   ├── promotion/       # 프로모션 관련 컴포넌트
│   ├── queue/           # 큐 관련 컴포넌트
│   ├── user/            # 사용자 관련 컴포넌트
│   └── admin/           # 관리자 컴포넌트
├── pages/               # 페이지 컴포넌트
│   └── admin/           # 관리자 페이지
├── services/            # API 서비스 레이어
├── hooks/               # 커스텀 React Hooks
├── types/               # TypeScript 타입 정의
├── utils/               # 유틸리티 함수
├── config/              # 설정 파일
├── App.tsx              # 메인 앱 컴포넌트
└── main.tsx             # 앱 진입점
```

## 주요 기능

### 1. 프로모션 목록
- 11번가 스타일 카드 레이아웃
- 실시간 카운트다운 타이머
- 할인율/가격 표시
- 재고 현황

### 2. 큐 시스템
- 실시간 대기 순번 표시
- 3초마다 자동 폴링
- 상태별 UI (WAITING/PROCEED/EXPIRED)
- 5분 구매 타이머

### 3. 사용자 인증
- 로그인/회원가입
- JWT 토큰 관리
- 자동 리다이렉트

### 4. 주문 관리
- 주문 생성
- 주문 내역 조회
- 잔액 관리

## API 통신

모든 API는 표준 응답 형식을 따릅니다:

```typescript
{
  resultType: 'SUCCESS' | 'ERROR',
  data: T | null,
  error: { code: string, message: string } | null
}
```

## 디자인 가이드

11번가 타임딜 디자인을 기반으로 한 색상 팔레트:

- **Primary Red**: `#ff0038` - 세일/할인
- **Success Green**: `#249356` - PROCEED 상태
- **Warning Orange**: `#ff8100` - WAITING 상태
- **Primary Blue**: `#0b83e6` - 링크/정보

자세한 디자인 가이드는 프로젝트 루트의 `DESIGN_*.md` 파일 참조.

## 개발 규칙

### 커밋 메시지
```
<type>(front): <description>

Type: feat, fix, perf, refactor, docs, test, build, ci, chore, style
```

예시:
- `feat(front): 프로모션 목록 페이지 추가`
- `fix(front): 타이머 카운트다운 버그 수정`

## 빌드

```bash
npm run build
```

빌드 결과물: `dist/` 디렉토리

## 테스트

```bash
npm run test
```

## 라이선스

Beyond Software Camp 21기 - 2팀
