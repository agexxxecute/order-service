package com.petproject.orderservice.controller;

import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;
import com.petproject.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderResponseDto createOrder(@RequestBody @Valid OrderCreateDto orderCreateDto) {
        return orderService.addOrder(orderCreateDto);
    }
}
