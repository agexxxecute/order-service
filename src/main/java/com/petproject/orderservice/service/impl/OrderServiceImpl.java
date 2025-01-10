package com.petproject.orderservice.service.impl;

import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.GeneratedNumberDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;
import com.petproject.orderservice.mapper.OrderMapper;
import com.petproject.orderservice.model.Book;
import com.petproject.orderservice.model.Order;
import com.petproject.orderservice.repository.OrderRepository;
import com.petproject.orderservice.service.OrderService;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RestTemplate restTemplate;

    private final String NUMBER_GENERATE_URL = "http://localhost:8000/numbers";

    @Override
    public OrderResponseDto addOrder(OrderCreateDto orderCreateDto) {
        Order order = orderMapper.toEntity(orderCreateDto);

        order.setOrderNumber(generateNumberRest());

        Optional<BigDecimal> totalPrice = order.getBooks().stream()
                .map(Book::getPrice)
                .reduce(BigDecimal::add);
        order.setTotalPrice(totalPrice.orElse(BigDecimal.ZERO));

        return orderMapper.toDto(orderRepository.save(order));
    }

    private String generateNumberRest(){
        ResponseEntity<GeneratedNumberDto> orderNumberEntity = restTemplate.getForEntity(NUMBER_GENERATE_URL, GeneratedNumberDto.class);
        return orderNumberEntity.getBody().orderNumber();
    }
}
