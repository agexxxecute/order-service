package com.petproject.orderservice.service.impl;

import com.petproject.orderservice.dto.request.OrderByDateBetweenRequestDto;
import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.GeneratedNumberDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;
import com.petproject.orderservice.exception.NotFoundException;
import com.petproject.orderservice.mapper.OrderMapper;
import com.petproject.orderservice.model.Book;
import com.petproject.orderservice.model.Order;
import com.petproject.orderservice.repository.OrderRepository;
import com.petproject.orderservice.service.OrderService;
import com.petproject.orderservice.util.ExceptionMessage;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final String NUMBER_GENERATE_URL = "http://localhost:8000/numbers";

    @Override
    @Transactional
    public OrderResponseDto addOrder(OrderCreateDto orderCreateDto) {
        Order order = orderMapper.toEntity(orderCreateDto);

        order.setOrderNumber(generateNumberRest());

        Optional<BigDecimal> totalPrice = order.getBooks().stream()
                .map(Book::getPrice)
                .reduce(BigDecimal::add);
        order.setTotalPrice(totalPrice.orElse(BigDecimal.ZERO));


        Order savedOrder = orderRepository.save(order);
        kafkaInfoSend(savedOrder.getTotalPrice());
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderMapper.toDto(orderRepository.findAll());
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order foundOrder = orderRepository.findById(id).orElseThrow(
            () -> new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND.getDescription())
        );
        return orderMapper.toDto(foundOrder);
    }

    @Override
    public List<OrderResponseDto> getOrderByDateBetween(OrderByDateBetweenRequestDto orderByDateBetweenRequestDto) {
        return orderMapper.toDto(orderRepository.getOrderByDateBetween(orderByDateBetweenRequestDto.startDate(), orderByDateBetweenRequestDto.endDate()));
    }

    private String generateNumberRest(){
        ResponseEntity<GeneratedNumberDto> orderNumberEntity = restTemplate.getForEntity(NUMBER_GENERATE_URL, GeneratedNumberDto.class);
        return orderNumberEntity.getBody().orderNumber();
    }

    private void kafkaInfoSend(BigDecimal totalPrice) {
        kafkaTemplate.send("order-create", totalPrice.toString());
    }
}
