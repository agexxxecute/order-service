package com.petproject.orderservice.mapper;

import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;
import com.petproject.orderservice.model.Order;
import com.petproject.orderservice.util.OrderUtil;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class OrderMapperTest {
    private OrderMapper orderMapper;
    private Order order;
    private OrderCreateDto orderCreateDto;

    @BeforeEach
    void setUp() {
        orderMapper = Mappers.getMapper(OrderMapper.class);

        order = OrderUtil.getValidOrder();
        orderCreateDto = OrderUtil.getValidOrderCreateDto();
    }

    @Test
    @DisplayName("OrderCreateDto to Order")
    void orderCreateDtoToOrder() {
        Order mappedOrder = orderMapper.toEntity(orderCreateDto);

        Assertions.assertEquals(mappedOrder.getCustomerName(), order.getCustomerName());
    }

    @Test
    @DisplayName("Order to OrderResponseDto")
    void orderToOrderResponseDto() {
        OrderResponseDto mappedOrderResponseDto = orderMapper.toDto(order);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(mappedOrderResponseDto.orderNumber()).isEqualTo(order.getOrderNumber());
            softAssertions.assertThat(mappedOrderResponseDto.orderDate()).isEqualTo(order.getOrderDate());
            softAssertions.assertThat(mappedOrderResponseDto.customerName()).isEqualTo(order.getCustomerName());
        });
    }

    @Test
    @DisplayName("Order List to OrderResponseDtoList")
    void orderListToOrderResponseDtoList() {
        List<OrderResponseDto> mappedOrderResponseDtoList = orderMapper.toDto(List.of(order));

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(mappedOrderResponseDtoList.get(0).orderNumber()).isEqualTo(order.getOrderNumber());
            softAssertions.assertThat(mappedOrderResponseDtoList.get(0).orderDate()).isEqualTo(order.getOrderDate());
            softAssertions.assertThat(mappedOrderResponseDtoList.get(0).customerName()).isEqualTo(order.getCustomerName());
        });
    }

}