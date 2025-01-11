package com.petproject.orderservice.service;

import com.petproject.orderservice.dto.request.OrderByDateBetweenRequestDto;
import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;
import java.util.List;

public interface OrderService {
    OrderResponseDto addOrder(OrderCreateDto orderCreateDto);

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto getOrderById(Long id);

    List<OrderResponseDto> getOrderByDateBetween(OrderByDateBetweenRequestDto orderByDateBetweenRequestDto);
}
