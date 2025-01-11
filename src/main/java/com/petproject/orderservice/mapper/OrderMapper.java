package com.petproject.orderservice.mapper;

import com.petproject.orderservice.dto.request.OrderCreateDto;
import com.petproject.orderservice.dto.response.OrderResponseDto;
import com.petproject.orderservice.model.Order;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderCreateDto orderCreateDto);

    OrderResponseDto toDto(Order order);

    List<OrderResponseDto> toDto(List<Order> orders);
}
