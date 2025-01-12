package com.petproject.orderservice.service;

import com.petproject.orderservice.dto.request.OrderByDateBetweenRequestDto;
import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;
import java.util.List;

/**
 * Интерфейс, содержащий бизнес-логику для работы с заказами.
 *
 * @author Egor Nazarev
 */
public interface OrderService {

    /**
     * Метод для добавления заказа
     * @param orderCreateDto входная информация о заказе
     * @return информация о созданном заказе
     */
    OrderResponseDto addOrder(OrderCreateDto orderCreateDto);

    /**
     * Метод для получения списка всех заказов
     * @return список заказов
     */
    List<OrderResponseDto> getAllOrders();

    /**
     * Метод для поиска заказа по id
     * @param id идентификатор заказа
     * @return информация о найденном заказе
     */
    OrderResponseDto getOrderById(Long id);

    /**
     * Метод для поиска заказов в заданном временном промежутке
     * @param orderByDateBetweenRequestDto информация о временном промежутке
     * @return список найденных заказов
     */
    List<OrderResponseDto> getOrderByDateBetween(OrderByDateBetweenRequestDto orderByDateBetweenRequestDto);
}
