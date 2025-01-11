package com.petproject.orderservice.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petproject.orderservice.dto.request.OrderByDateBetweenRequestDto;
import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;
import com.petproject.orderservice.exception.BadRequestException;
import com.petproject.orderservice.exception.InternalServerErrorException;
import com.petproject.orderservice.exception.NotFoundException;
import com.petproject.orderservice.service.OrderService;
import com.petproject.orderservice.util.ConstantUtil;
import com.petproject.orderservice.util.ExceptionMessage;
import com.petproject.orderservice.util.OrderUtil;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {
    @MockitoBean
    OrderService orderService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private final String ORDERS_URL = "/orders";
    private final String GET_ALL_ORDERS_URL = "/orders/all";
    private final String GET_ORDER_BY_DATE_BETWEEN = "/orders/period";

    private static OrderResponseDto orderResponseDto;
    private static OrderByDateBetweenRequestDto orderByDateBetweenRequestDto;
    private static OrderCreateDto orderCreateDto;

    @BeforeAll
    static void setUp() {
        orderResponseDto = OrderUtil.getValidOrderResponseDto();
        orderByDateBetweenRequestDto = OrderUtil.getValidOrderByDateBetweenRequestDto();
        orderCreateDto = OrderUtil.getValidOrderCreateDto();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should find all orders")
    void getAllOrdersSuccessfully(){
        when(orderService.getAllOrders()).thenReturn(List.of(orderResponseDto));

        mockMvc.perform(get(GET_ALL_ORDERS_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        verify(orderService).getAllOrders();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Not Found and status 404")
    void getAllOrdersNotFound(){
        when(orderService.getAllOrders()).thenThrow(new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND.getDescription()));

        mockMvc.perform(get(GET_ALL_ORDERS_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.ORDER_NOT_FOUND.getDescription()))
            .andReturn();

        verify(orderService).getAllOrders();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Internal Server Error and status 500")
    void getAllOrdersInternalServerError(){
        when(orderService.getAllOrders()).thenThrow(new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()));

        mockMvc.perform(get(GET_ALL_ORDERS_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()))
            .andReturn();

        verify(orderService).getAllOrders();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should find order by ID")
    void getOrderByIdSuccessfully(){
        when(orderService.getOrderById(ConstantUtil.VALID_ID)).thenReturn(orderResponseDto);

        mockMvc.perform(get(ORDERS_URL + "?id=" + ConstantUtil.VALID_ID)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        verify(orderService).getOrderById(ConstantUtil.VALID_ID);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Not Found and status 404")
    void getOrderByIdNotFound(){
        when(orderService.getOrderById(ConstantUtil.INVALID_ID)).thenThrow(new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND.getDescription()));

        mockMvc.perform(get(ORDERS_URL + "?id=" + ConstantUtil.INVALID_ID)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.ORDER_NOT_FOUND.getDescription()))
            .andReturn();

        verify(orderService).getOrderById(ConstantUtil.INVALID_ID);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Internal Server Error and status 500")
    void getOrderByIdInternalServerError(){
        when(orderService.getOrderById(ConstantUtil.VALID_ID)).thenThrow(new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()));

        mockMvc.perform(get(ORDERS_URL + "?id=" + ConstantUtil.VALID_ID)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()))
            .andReturn();

        verify(orderService).getOrderById(ConstantUtil.VALID_ID);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should find orders by date between")
    void getOrderByDateBetweenSuccessfully(){
        when(orderService.getOrderByDateBetween(orderByDateBetweenRequestDto)).thenReturn(List.of(orderResponseDto));

        mockMvc.perform(get(GET_ORDER_BY_DATE_BETWEEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderByDateBetweenRequestDto)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        verify(orderService).getOrderByDateBetween(orderByDateBetweenRequestDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Bad Request and status 400")
    void getOrderByDateBetweenBadRequest(){
        when(orderService.getOrderByDateBetween(orderByDateBetweenRequestDto)).thenThrow(new BadRequestException(ExceptionMessage.BAD_REQUEST.getDescription()));

        mockMvc.perform(get(GET_ORDER_BY_DATE_BETWEEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderByDateBetweenRequestDto)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.BAD_REQUEST.getDescription()))
            .andReturn();

        verify(orderService).getOrderByDateBetween(orderByDateBetweenRequestDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Not Found and status 404")
    void getOrderByDateNotFound(){
        when(orderService.getOrderByDateBetween(orderByDateBetweenRequestDto)).thenThrow(new NotFoundException(ExceptionMessage.ORDER_NOT_FOUND.getDescription()));

        mockMvc.perform(get(GET_ORDER_BY_DATE_BETWEEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderByDateBetweenRequestDto)))
            .andExpect(status().isNotFound())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.ORDER_NOT_FOUND.getDescription()))
            .andReturn();

        verify(orderService).getOrderByDateBetween(orderByDateBetweenRequestDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Internal Server Error and status 500")
    void getOrderByDateInternalServerError(){
        when(orderService.getOrderByDateBetween(orderByDateBetweenRequestDto)).thenThrow(new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()));

        mockMvc.perform(get(GET_ORDER_BY_DATE_BETWEEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderByDateBetweenRequestDto)))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()))
            .andReturn();

        verify(orderService).getOrderByDateBetween(orderByDateBetweenRequestDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should create order")
    void createOrderSuccessfully(){
        when(orderService.addOrder(orderCreateDto)).thenReturn(orderResponseDto);

        mockMvc.perform(post(ORDERS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderCreateDto)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn();

        verify(orderService).addOrder(orderCreateDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Bad Request and status 400")
    void createOrderBadRequest(){
        when(orderService.addOrder(orderCreateDto)).thenThrow(new BadRequestException(ExceptionMessage.BAD_REQUEST.getDescription()));

        mockMvc.perform(post(ORDERS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderCreateDto)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.BAD_REQUEST.getDescription()))
            .andReturn();

        verify(orderService).addOrder(orderCreateDto);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return Internal Server Error and status 500")
    void createOrderInternalServerError(){
        when(orderService.addOrder(orderCreateDto)).thenThrow(new InternalServerErrorException(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()));

        mockMvc.perform(post(ORDERS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderCreateDto)))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.errorMessage").value(ExceptionMessage.INTERNAL_SERVER_ERROR.getDescription()))
            .andReturn();

        verify(orderService).addOrder(orderCreateDto);
    }
}