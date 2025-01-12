package com.petproject.orderservice.controller;

import com.petproject.orderservice.dto.request.OrderByDateBetweenRequestDto;
import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;
import com.petproject.orderservice.exception.handler.ErrorResponseDto;
import com.petproject.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с заказами.
 * Предоставляет конечные точки для создания и получения заказов
 *
 * @author Egor Nazarev
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;


    @Operation(description = "Позволяет создать заказ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Создание заказа произошло успешно"),
        @ApiResponse(responseCode = "400", description = "Ошибка в данных запроса", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        })})
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDto createOrder(@RequestBody @Valid OrderCreateDto orderCreateDto) {
        return orderService.addOrder(orderCreateDto);
    }

    @Operation(description = "Позволяет найти заказ по id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Заказ найден успешно"),
        @ApiResponse(responseCode = "404", description = "Заказ с указанным id не найден", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        })})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderResponseDto getOrderById(@RequestParam Long id) {
        return orderService.getOrderById(id);
    }

    @Operation(description = "Позволяет получить все заказы")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Заказы найдены успешно"),
        @ApiResponse(responseCode = "404", description = "Не найдено ни одного заказа", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        })})
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Operation(description = "Позволяет получить заказы за определенный период")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Заказы найдены успешно"),
        @ApiResponse(responseCode = "400", description = "Ошибка в данных запроса", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "404", description = "Не найдено ни одного заказа", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        }),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера", content = {
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
        })})
    @GetMapping("/period")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDto> getOrderByDateBetween(@RequestBody @Valid OrderByDateBetweenRequestDto orderByDateBetweenRequestDto){
        return orderService.getOrderByDateBetween(orderByDateBetweenRequestDto);
    }
}
