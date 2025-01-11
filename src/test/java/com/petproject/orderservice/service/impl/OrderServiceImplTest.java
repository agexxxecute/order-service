package com.petproject.orderservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.petproject.orderservice.dto.request.OrderByDateBetweenRequestDto;
import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.GeneratedNumberDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;
import com.petproject.orderservice.exception.InternalServerErrorException;
import com.petproject.orderservice.exception.NotFoundException;
import com.petproject.orderservice.mapper.OrderMapper;
import com.petproject.orderservice.model.Order;
import com.petproject.orderservice.repository.OrderRepository;
import com.petproject.orderservice.util.ConstantUtil;
import com.petproject.orderservice.util.ExceptionMessage;
import com.petproject.orderservice.util.OrderUtil;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private OrderServiceImpl orderService;

    private final String NUMBER_GENERATE_URL = "http://localhost:8000/numbers";

    private static Order order;
    private static OrderCreateDto orderCreateDto;
    private static OrderByDateBetweenRequestDto orderByDateBetweenRequestDto;
    private static OrderResponseDto orderResponseDto;
    private static GeneratedNumberDto generatedNumberDto;

    @BeforeAll
    static void setUp() {
        order = OrderUtil.getValidOrder();
        orderCreateDto = OrderUtil.getValidOrderCreateDto();
        orderByDateBetweenRequestDto = OrderUtil.getValidOrderByDateBetweenRequestDto();
        orderResponseDto = OrderUtil.getValidOrderResponseDto();
        generatedNumberDto = new GeneratedNumberDto(ConstantUtil.VALID_ORDER_NUMBER);
    }

    @Test
    @DisplayName("Should create order")
    void createOrderSuccessfully() {
        when(orderMapper.toEntity(orderCreateDto)).thenReturn(order);
        when(restTemplate.getForEntity(NUMBER_GENERATE_URL, GeneratedNumberDto.class)).thenReturn(new ResponseEntity<>(generatedNumberDto, HttpStatus.OK));
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.toDto(order)).thenReturn(orderResponseDto);

        orderService.addOrder(orderCreateDto);

        verify(orderMapper).toEntity(orderCreateDto);
        verify(restTemplate).getForEntity(NUMBER_GENERATE_URL, GeneratedNumberDto.class);
        verify(orderRepository).save(order);
        verify(orderMapper).toDto(order);
    }

    @Test
    @DisplayName("Should return Internal Server Error")
    void shouldReturnInternalServerError() {
        when(orderMapper.toEntity(orderCreateDto)).thenReturn(order);
        when(restTemplate.getForEntity(NUMBER_GENERATE_URL, GeneratedNumberDto.class)).thenThrow(new InternalServerErrorException(
            ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()));

        assertThrows(InternalServerErrorException.class, () -> orderService.addOrder(orderCreateDto));

        verify(orderMapper).toEntity(orderCreateDto);
        verify(restTemplate).getForEntity(NUMBER_GENERATE_URL, GeneratedNumberDto.class);
    }

    @Test
    @DisplayName("Should get all orders")
    void getAllOrdersSuccessfully() {
        List<Order> orderList = List.of(order);
        List<OrderResponseDto> orderResponseDtoList = List.of(orderResponseDto);
        when(orderRepository.findAll()).thenReturn(orderList);
        when(orderMapper.toDto(orderList)).thenReturn(orderResponseDtoList);

        orderService.getAllOrders();

        verify(orderRepository).findAll();
        verify(orderMapper).toDto(orderList);
    }

    @Test
    @DisplayName("Should return Not Found")
    void getAllOrdersNotFound() {
        when(orderRepository.findAll()).thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> orderService.getAllOrders());

        verify(orderRepository).findAll();
    }

    @Test
    @DisplayName("Should find order by ID")
    void findOrderByIdSuccessfully() {
        when(orderRepository.findById(ConstantUtil.VALID_ID)).thenReturn(Optional.of(order));
        when(orderMapper.toDto(order)).thenReturn(orderResponseDto);

        orderService.getOrderById(ConstantUtil.VALID_ID);

        verify(orderRepository).findById(ConstantUtil.VALID_ID);
        verify(orderMapper).toDto(order);
    }

    @Test
    @DisplayName("Should return Not Found")
    void findOrderByIdNotFound() {
        when(orderRepository.findById(ConstantUtil.INVALID_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> orderService.getOrderById(ConstantUtil.INVALID_ID));

        verify(orderRepository).findById(ConstantUtil.INVALID_ID);
    }

    @Test
    @DisplayName("Should find orders by date between")
    void findOrderByDateBetweenSuccessfully() {
        List<Order> orderList = List.of(order);
        List<OrderResponseDto> orderResponseDtoList = List.of(orderResponseDto);
        when(orderRepository.getOrderByDateBetween(orderByDateBetweenRequestDto.startDate(), orderByDateBetweenRequestDto.endDate())).thenReturn(List.of(order));
        when(orderMapper.toDto(orderList)).thenReturn(orderResponseDtoList);

        orderService.getOrderByDateBetween(orderByDateBetweenRequestDto);

        verify(orderRepository).getOrderByDateBetween(orderByDateBetweenRequestDto.startDate(), orderByDateBetweenRequestDto.endDate());
        verify(orderMapper).toDto(orderList);
    }

    @Test
    @DisplayName("Should return Not Found")
    void findOrderByDateBetweenNotFound() {
        when(orderRepository.getOrderByDateBetween(orderByDateBetweenRequestDto.startDate(), orderByDateBetweenRequestDto.endDate())).thenReturn(List.of());

        assertThrows(NotFoundException.class, () -> orderService.getOrderByDateBetween(orderByDateBetweenRequestDto));

        verify(orderRepository).getOrderByDateBetween(orderByDateBetweenRequestDto.startDate(), orderByDateBetweenRequestDto.endDate());
    }
}