// User Types
export interface User {
  id: number;
  email: string;
  name: string;
  balance: number;
  createdAt: string;
  updatedAt: string;
}

export interface SignInRequest {
  email: string;
  password: string;
}

export interface SignUpRequest {
  email: string;
  password: string;
  name: string;
}

export interface SignInResponse {
  user: User;
  token?: string;
}
