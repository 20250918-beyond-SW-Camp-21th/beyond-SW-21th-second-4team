package com.ohgiraffers.account.core.domain;

import com.ohgiraffers.account.core.api.controller.v1.response.MyPageResponse;
import com.ohgiraffers.account.core.api.controller.v1.response.OrderDetailResponse;
import com.ohgiraffers.account.core.api.controller.v1.response.SignInResponse;
import com.ohgiraffers.account.security.JwtTokenProvider;
import com.ohgiraffers.account.storage.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    UserService userService;



    @DisplayName("로그인 정상 반환")
    @Test
    void getSignIn() {
        //given
        String email = "test1@naver.com";
        String password = "Password1!";

        when(passwordEncoder.encode(password)).thenReturn("!@#$");
        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(email, encodedPassword, "홍길동");
        ReflectionTestUtils.setField(user, "id", 1L);

        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(password, encodedPassword))
                .thenReturn(true);

        when(jwtTokenProvider.createToken(user.getEmail(),"USER",user.getId()))
                .thenReturn("token");

        //when
        SignInResponse result = userService.signIn(email, password);

        //then
        Assertions.assertEquals(1L, result.userId());
        Assertions.assertEquals("token", result.token());
    }

    @DisplayName("회원가입 정상 처리")
    @Test
    void getSignUp() {
        String email = "test1@naver.com";
        String password = "Password1!";
        String name = "홍길동";

        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn("!@#$");

        userService.signUp(email, password, name);

        // existsByEmail 호출됐는지 확인
        verify(userRepository).existsByEmail(email);

        // save에 전달된 User 객체 캡처
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        User savedUser = captor.getValue();

        // 저장된 값 검증
        Assertions.assertEquals(email, savedUser.getEmail());
        Assertions.assertEquals("!@#$", savedUser.getPassword());
        Assertions.assertEquals(name, savedUser.getName());
    }

    @DisplayName("마이페이지 정상 반환")
    @Test
    void getMe(){
        //given
        Long id = 1L;
        User user = new User("홍길동",50000,10000);
        ReflectionTestUtils.setField(user, "id", id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        //when
        MyPageResponse test = userService.getMe(id);
        //then
        Assertions.assertEquals(test.name(),"홍길동");
        Assertions.assertEquals(test.money(),50000);
        Assertions.assertEquals(test.total_saved(),10000);
    }

    @DisplayName("마이페이지 실패 반환")
    @Test
    void getFalseMe(){
        //given
        Long id1 = 1L;
        Long id2 = 2L;
        User user = new User("홍길동",50000,10000);
        ReflectionTestUtils.setField(user, "id", id1);
        when(userRepository.findById(id1)).thenReturn(Optional.of(user));
        //when
        MyPageResponse test = userService.getMe(id2);
        //then
        Assertions.assertEquals(test.name(),"홍길동");
        Assertions.assertEquals(test.money(),50000);
        Assertions.assertEquals(test.total_saved(),10000);
    }
//
//    @DisplayName("마이페이지 주문목록 성공 반환")
//    @Test
//    void getMeOrders(){
//        //given
//        Long id = 1L;
//        String email = "test1@naver.com";
//        String password = "Password1!";
//        User user = new User(email,password,"홍길동");
//        ReflectionTestUtils.setField(user, "id", id);
//
//        Order order = Order.create(1L, 5000);
//        ReflectionTestUtils.setField(order, "id", 1L);
//
//        when(orderRepository.findByUserId(id)).thenReturn(List.of(order));
//
//        OrderDetail detail = OrderDetail.of(1L, 1L, 2, 1000,"프로모션1","test.jpg");
////        ReflectionTestUtils.setField(detail, "promotionName", "프로모션1");
////        ReflectionTestUtils.setField(detail, "imageUrl", "test.jpg");
//
//        when(orderDetailRepository.findByOrderId(order.getId())).thenReturn(Optional.of(detail));
//        //when
//        OrderDetailResponse test = userService.getMeOrders(id);
//        //then
////        Assertions.assertEquals(order.getUserId(),user.getId()); //사용자가 주문한 id가 맞는지 확인
////        Assertions.assertEquals(order.getId(),detail.getOrderId()); // 주문 id와 detail.id가 맞는지 확인
//        Assertions.assertEquals(test.myPageOrderResponseList().get(0).PromotionName(),"프로모션1");
//        Assertions.assertEquals(test.myPageOrderResponseList().get(0).image(),"test.jpg");
//        Assertions.assertEquals(test.myPageOrderResponseList().get(0).price(),2000);
//    }
//
//
//
//    @DisplayName("토큰 값 정상 반환")
//    @Test
//    void getTrueUserByToken() {
//        // given
//        Long userId = 1L;
//        String token = "getUserByToken_token";
//        String key = "UserToken:" + userId;
//        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
//        when(valueOperations.get(key)).thenReturn(token);
//
//        // when
//        Boolean verify = userService.verifyToken(userId, token);
//
//        // then
//        Assertions.assertTrue(verify);
//    }
//
//    @DisplayName("토큰 값 실패 반환")
//    @Test
//    void getFalseUserByToken() {
//        //given
//        Long userId = 1L;
//        String token1 = "success_token";
//        String token2 = "failure_token";
//        when(valueOperations.get(userId)).thenReturn(token1);
//
//        // when
//        Boolean verify = userService.verifyToken(userId, token2);
//
//        // then
//        Assertions.assertTrue(verify);
//    }




}