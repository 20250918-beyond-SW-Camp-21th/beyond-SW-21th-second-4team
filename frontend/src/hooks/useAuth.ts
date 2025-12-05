import { useState, useEffect } from 'react';
import type { User } from '../types/user';
import { userService } from '../services/userService';

export const useAuth = () => {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Check if user is logged in on mount
    const userId = localStorage.getItem('userId');
    if (userId) {
      loadUser(parseInt(userId));
    } else {
      setLoading(false);
    }
  }, []);

  const loadUser = async (userId: number) => {
    try {
      const myPageData = await userService.getUserProfile(userId);
      // MyPageResponse를 User 타입으로 변환
      const userData: User = {
        id: userId,
        email: localStorage.getItem('userEmail') || '',
        name: myPageData.name,
        money: myPageData.money,
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString(),
      };
      setUser(userData);
    } catch (error) {
      console.error('Failed to load user:', error);
      localStorage.removeItem('userId');
      localStorage.removeItem('authToken');
      localStorage.removeItem('userEmail');
    } finally {
      setLoading(false);
    }
  };

  const signIn = async (email: string, password: string) => {
    try {
      const response = await userService.signIn(email, password);

      console.log('로그인 응답:', response);

      // 토큰과 userId 저장
      localStorage.setItem('authToken', response.token);
      localStorage.setItem('userId', response.userId.toString());
      localStorage.setItem('userEmail', email);

      // 실제 사용자 정보 조회
      const myPageData = await userService.getUserProfile(response.userId);

      // MyPageResponse를 User 타입으로 변환
      const userData: User = {
        id: response.userId,
        email: email,
        name: myPageData.name,
        money: myPageData.money,
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString(),
      };

      setUser(userData);

      console.log('로그인 성공, 사용자 데이터:', userData);

      return userData;
    } catch (error) {
      console.error('로그인 에러:', error);
      throw error;
    }
  };

  const signUp = async (email: string, password: string, name: string) => {
    try {
      await userService.signUp(email, password, name);
      // 회원가입 성공 후 자동 로그인은 하지 않음
      return;
    } catch (error) {
      throw error;
    }
  };

  const login = (user: User, token: string) => {
    setUser(user);
    localStorage.setItem('userId', user.id.toString());
    localStorage.setItem('authToken', token);
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem('userId');
    localStorage.removeItem('authToken');
  };

  return {
    user,
    loading,
    signIn,
    signUp,
    login,
    logout,
  };
};
