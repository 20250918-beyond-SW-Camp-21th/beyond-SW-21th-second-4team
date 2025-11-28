package com.ohgiraffers.timedeal.core.api.controller.v1.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

//@Getter
//@AllArgsConstructor
//public class OrderDetailResponse {
//    List<MyPageOrderResponse> myPageOrderResponseList;
//
//}

public record OrderDetailResponse(List<MyPageOrderResponse> myPageOrderResponseList){

}