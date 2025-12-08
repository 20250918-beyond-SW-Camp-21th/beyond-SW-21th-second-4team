<<<<<<<< HEAD:account/src/main/java/com/ohgiraffers/account/core/api/controller/v1/response/OrderDetailResponse.java
package com.ohgiraffers.account.core.api.controller.v1.response;

========
package com.ohgiraffers.order.core.api.controller.v1.response;
>>>>>>>> dev:order/src/main/java/com/ohgiraffers/order/core/api/controller/v1/response/OrderDetailResponse.java

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record OrderDetailResponse(
        @Schema(description = "주문내역 리스트",requiredMode = REQUIRED)
        List<MyPageOrderResponse> myPageOrderResponseList
){

}