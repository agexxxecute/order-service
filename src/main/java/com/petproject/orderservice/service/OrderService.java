package com.petproject.orderservice.service;

import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;

public interface OrderService {
    OrderResponseDto addOrder(OrderCreateDto orderCreateDto);
}
