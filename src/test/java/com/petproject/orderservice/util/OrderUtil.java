package com.petproject.orderservice.util;

import com.petproject.orderservice.dto.request.OrderByDateBetweenRequestDto;
import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;
import com.petproject.orderservice.model.Order;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderUtil {
    public static Order getValidOrder() {
        return Order.builder()
            .id(ConstantUtil.VALID_ID)
            .orderNumber(ConstantUtil.VALID_ORDER_NUMBER)
            .totalPrice(ConstantUtil.VALID_ORDER_PRICE)
            .customerName(ConstantUtil.VALID_CUSTOMER_NAME)
            .books(List.of(BookUtil.getValidBook()))
            .build();
    }

    public static OrderCreateDto getValidOrderCreateDto() {
        return OrderCreateDto.builder()
            .customerName(ConstantUtil.VALID_CUSTOMER_NAME)
            .books(List.of(BookUtil.getValidBookCreateDto()))
            .build();
    }

    public static OrderResponseDto getValidOrderResponseDto() {
        return OrderResponseDto.builder()
            .orderNumber(ConstantUtil.VALID_ORDER_NUMBER)
            .totalPrice(ConstantUtil.VALID_ORDER_PRICE)
            .customerName(ConstantUtil.VALID_CUSTOMER_NAME)
            .books(List.of(BookUtil.getValidBookResponseDto()))
            .build();
    }

    public static OrderByDateBetweenRequestDto getValidOrderByDateBetweenRequestDto() {
        return OrderByDateBetweenRequestDto.builder()
            .startDate(ConstantUtil.VALID_ORDER_DATE)
            .endDate(ConstantUtil.VALID_ORDER_END_DATE)
            .build();
    }
}
