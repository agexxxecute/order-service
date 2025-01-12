package com.petproject.orderservice.mapper;

import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;
import com.petproject.orderservice.model.Order;
import java.util.List;
import org.mapstruct.Mapper;

/**
 * Интерфейс для преобразования объектов типа Order в Dto и обратно.
 *
 * @author Egor Nazarev
 */
@Mapper (componentModel = "spring")
public interface OrderMapper {

    /**
     * Метод лоя преобразования OrderCreateDto в Order
     * @param orderCreateDto входная информация о заказе
     * @return объект Order
     */
    Order toEntity(OrderCreateDto orderCreateDto);

    /**
     * Метод для преобразования Order в OrderResponseDto
     * @param order объект Order
     * @return информация о заказе
     */
    OrderResponseDto toDto(Order order);


    /**
     * Метод для преобразования списка Order в список OrderResponseDto
     * @param orders список Order
     * @return список OrderResponseDto
     */
    List<OrderResponseDto> toDto(List<Order> orders);
}
