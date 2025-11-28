package com.ohgiraffers.timedeal.core.api.controller.v1.response;

import com.ohgiraffers.timedeal.core.domain.User;
import com.ohgiraffers.timedeal.core.domain.product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

//@Getter
//@AllArgsConstructor
//public class MyPageResponse {
//    // 1. 마이페이지 + 주문내역
////    private User user;
////    private List<MyPageOrderResponse> myPageOrders;
//
//    //2. 마이페이지 따로
//    private String name;
//    private int money;
//    private int total_saved;
//
//}

public record MyPageResponse(String name, int money, int total_saved){

}