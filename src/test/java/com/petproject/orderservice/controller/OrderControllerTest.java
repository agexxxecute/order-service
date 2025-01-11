package com.petproject.orderservice.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petproject.orderservice.dto.response.OrderResponseDto;
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

    private static OrderResponseDto orderResponseDto;

    @BeforeAll
    static void setUp() {
        orderResponseDto = OrderUtil.getValidOrderResponseDto();
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
}